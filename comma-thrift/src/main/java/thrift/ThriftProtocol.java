package org.ebenso.comma.core.thrift;

/**
 * JSON and Binary protocol to serialize and deserialize data buffer(protocol buffer)
 * Thrift support other protocol too.
 * for more reference about protocol buffer refer google's protocol buffer.
 * @author kumar harsha (Kumar.harsha@oyorooms.com)
 * @version $Id$
 * @since 0.7.22
 * @see <a href="http://github.com/oyoroomd">http:github.com</a>
 */
public enum ThriftProtocol {

    JSONProtocol("JSONProtocol"),
    BinaryProtocol("BinaryProtocol");

    String protocol;
    ThriftProtocol(String protocol) {
        this.protocol = protocol;
    }
}
