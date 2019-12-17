package org.ebenso.comma.core.thrift;

import com.google.common.base.Optional;
import com.google.common.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for storing action related data.
 *
 * This usually means a request, a status, and an optional result.
 *
 * RequestType must implement a sane equals and hashCode, otherwise other components will fail or provide
 * poor performance.
 */
public class Action<RequestType, ResultType> {

    String name;
    private final static Logger logger = LoggerFactory.getLogger(Action.class);

    final private RequestType request;
    // generic parameter hack
    private final TypeToken<RequestType> requestType;
    private final TypeToken<ResultType> resultType;
    private ActionResult<ResultType> result;

    public Action(RequestType request) {
        this.request = request;
        this.result = new ActionResult<ResultType>();
        resultType = new TypeToken<ResultType>(getClass()) {
        };
        requestType = new TypeToken<RequestType>(getClass()) {
        };
    }

    public Action(RequestType request, TypeToken<RequestType> requestType, TypeToken<ResultType> resultType) {
        this.request = request;
        this.result = new ActionResult<ResultType>();
        this.requestType = requestType;
        this.resultType = resultType;
    }

    public RequestType getRequest() {
        return request;
    }

    public ActionStatus getStatus() {
        return result.getStatus();
    }

    public Optional<ResultType> getResult() {
        return result.getResult();
    }

    /**
     * Tries to run itself with the given implementation and runner.
     */
    public void runWithImpl(ActionImpl<RequestType, ResultType> impl, InnerRunner innerRunner) {
        if (result.isConcreteResult()) {
            throw new RuntimeException("Can't rerun a finished action");
        }
        try {
            ActionResult<ResultType> tmp = impl.doRun(this, innerRunner);

            if (tmp.betterThan(result)) {
                result = tmp;
            }
        } catch (Exception e) {
            logger.info("Exception while running action, treating as " +
                    "COMMUNICATION_ERROR", e);
            result = new ActionResult<ResultType>(ActionStatus.COMMUNICATION_ERROR);
        }
    }

    public ActionParameterInfo getParameterInfo() {
        return new ActionParameterInfo(requestType, resultType);
    }

    /**
     * @see ActionResult::isConcreteResult
     */
    public boolean isConcreteResult() {
        return result.isConcreteResult();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Action)) {
            return false;
        }
        Action oth = (Action) obj;
        return request.equals(oth.getRequest());
    }

    @Override
    public int hashCode() {
        return request.hashCode();
    }

    @Override
    public String toString() {
        return "<<ACTION: " + request.toString() + ">>[" + getStatus() + "]";
    }
}
