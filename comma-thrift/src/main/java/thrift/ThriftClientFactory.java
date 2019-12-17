package org.ebenso.comma.core.thrift;

import com.google.common.reflect.TypeToken;

import org.ebenso.comma.exception.ExceptionHelper;
import org.ebenso.comma.exception.model.Exception;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * ThriftClientFacoty which responsible to provide the thrift client as per given type.
 * Uses internal cache for the same URL.
 * By default it uses JSONProtocol form JSONProtocol and BinaryProtocol
 * @author kumar harsha (Kumar.harsha@oyorooms.com)
 * @version $Id$
 * @since 0.7.22
 * @see <a href="http://github.com/oyoroomd">http:github.com</a>
 */
public class ThriftClientFactory implements ThriftCommunicationInterface {

    String name;
    private final static Logger logger = LoggerFactory.getLogger(ThriftClientFactory.class);
    private ThriftProtocol protocol;
    private Map<String, TTransport> activeTransports;
    private int timeOut;
    private Map<TypeToken, TServiceClientFactory<?>> clientFactoryMappings;

    public ThriftClientFactory() {
        activeTransports = new HashMap<>();
        clientFactoryMappings = new HashMap<>();
        protocol = ThriftProtocol.JSONProtocol; // default support json protocol
    }

    /**
     * @param url : thrift service base url used for communication
     * @return TProtocol
     * @throws TTransportException if transport creation fails (e.g. timeout)
     */
    protected TProtocol requestTransport(String url) throws TTransportException {

        // probably not thread safe, but we need it? Not atm.
        TTransport act;

        if (!activeTransports.containsKey(url)) {
            logger.error("Creating new transport for: " + url);
            activeTransports.put(url, new THttpClient(url));
        }

        act = activeTransports.get(url);

        if (!act.isOpen()) {
            act.open();
        }
        // THINK: always create new protocol?
        // default protocol
        if (ThriftProtocol.BinaryProtocol.equals(protocol)) {
            return new TBinaryProtocol(act);
        } else {
            return new TJSONProtocol(act);
        }
    }

    /**
     * configured/set the thrift protocol (only JSONProtocol and BinaryProtocol are supported)
     * @param protocol : ThriftProtocol
     */
    public void setProtocol(ThriftProtocol protocol) {
        this.protocol = protocol;
    }

    public ThriftProtocol getProtocol() {
        return this.protocol;
    }

    /**
     * Creates a client based on the TypeToken pointing to a Thrift interface class.
     * <p>
     * This method uses black magic, based on the structure and names of generated
     * cc.ecl.action.thrift classes. If that changes, this here breaks.
     *
     * @throws TTransportException    if transport creation fails (e.g. timeout)
     * @throws ThriftManagerException if factory creation fails (probably bad ifaceType parameter,
     *                                or Thrift internals changed)
     */
    @Override
    public <IFaceT> IFaceT initializeClient(String url, TypeToken<IFaceT> ifaceType) throws
            TTransportException {

        // assume same class loader
        ClassLoader classLoader = ifaceType.getRawType().getClassLoader();
        String factoryName = ifaceType.toString().replace("Iface", "Client$Factory");

        try {

            if (!clientFactoryMappings.containsKey(ifaceType)) {
                clientFactoryMappings.put(ifaceType, (TServiceClientFactory<?>) classLoader
                        .loadClass(factoryName).newInstance());
            }
        } catch (java.lang.Exception e) {
//            ExceptionHelper.propagate("IllegalAccessException while initializing: " +
//                    factoryName, e);
        }

        return (IFaceT) clientFactoryMappings.get(ifaceType).getClient(requestTransport(url));
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public static class ThriftManagerException extends Exception {
        public ThriftManagerException(String s) {
            super(s);
        }

        public ThriftManagerException(String s, IllegalAccessException e) {
            super(s, e);
        }
    }
}