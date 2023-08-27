package anonymous.dp.service.fo.config;

import anonymous.dp.service.fo.FoLdpFactory.FoLdpType;
import anonymous.dp.service.tool.Domain;

import java.util.Set;

/**
 * Frequency Oracle LDP client config.
 *
 * @author anonymous author
 * @date 2023/1/14
 */
public interface FoLdpConfig {
    /**
     * Gets the type.
     *
     * @return the type.
     */
    FoLdpType getType();

    /**
     * Gets the name.
     *
     * @return the name.
     */
    default String getName() {
        return getType().name();
    }

    /**
     * Gets the domain.
     *
     * @return the domain.
     */
    Domain getDomain();

    /**
     * Gets the domain set.
     *
     * @return the domain set.
     */
    default Set<String> getDomainSet() {
        return getDomain().getDomainSet();
    }

    /**
     * Gets the domain size d, i.e., |Ω|.
     *
     * @return the domain size d.
     */
    int getD();

    /**
     * Return the privacy parameter ε.
     *
     * @return the privacy parameter ε.
     */
    double getEpsilon();
}
