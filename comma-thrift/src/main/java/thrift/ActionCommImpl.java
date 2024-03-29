package org.ebenso.comma.core.thrift;

/**
 * Implementation of an action which requires a communication interface.
 */
public interface ActionCommImpl<ReqT, ResT, CommT> extends ActionImpl<ReqT, ResT> {
    public void setCommunicationInterface(CommT communicationInterface);

    public ActionCommImpl<ReqT, ResT, CommT> dup();
}