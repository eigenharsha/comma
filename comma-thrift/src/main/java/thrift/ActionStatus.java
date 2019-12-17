package org.ebenso.comma.core.thrift;

/**
 * Stores the current status of the action's result.
 */
public enum ActionStatus {
    PENDING, // or never tried
    SUCCEEDED, // final, concrete
    COMMUNICATION_ERROR, // can retry
    LOGIC_ERROR // final, concrete
}