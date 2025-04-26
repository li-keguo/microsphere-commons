package io.microsphere.management;


import io.microsphere.logging.Logger;
import io.microsphere.process.ProcessIdResolver;
import io.microsphere.util.Utils;

import java.util.List;

import static io.microsphere.logging.LoggerFactory.getLogger;
import static io.microsphere.process.ProcessIdResolver.UNKNOWN_PROCESS_ID;
import static io.microsphere.util.ServiceLoaderUtils.loadServicesList;

/**
 * Management Utility class
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see ManagementUtils
 * @since 1.0.0
 */
public abstract class ManagementUtils implements Utils {

    private static final Logger logger = getLogger(ManagementUtils.class);

    static final long currentProcessId = resolveCurrentProcessId();

    private static long resolveCurrentProcessId() {
        List<ProcessIdResolver> resolvers = loadServicesList(ProcessIdResolver.class);
        Long processId = null;
        for (ProcessIdResolver resolver : resolvers) {
            if (resolver.supports()) {
                if ((processId = resolver.current()) != null) {
                    log(resolver, processId);
                    break;
                }
            }
        }
        return processId == null ? UNKNOWN_PROCESS_ID : processId;
    }

    static void log(ProcessIdResolver resolver, Long processId) {
        if (logger.isTraceEnabled()) {
            logger.trace("The process id was resolved by ProcessIdResolver[class : '{}' , priority : {}] successfully : {}",
                    resolver.getClass().getName(), resolver.getPriority(), processId);
        }
    }

    /**
     * Get the process ID of current JVM
     *
     * @return If can't get the process ID , return <code>-1</code>
     */
    public static long getCurrentProcessId() {
        return currentProcessId;
    }

    private ManagementUtils() {
    }
}
