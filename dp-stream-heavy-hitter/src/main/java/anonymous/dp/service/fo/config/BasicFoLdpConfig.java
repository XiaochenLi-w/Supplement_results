package anonymous.dp.service.fo.config;

import anonymous.dp.service.fo.FoLdpFactory.FoLdpType;
import anonymous.dp.service.tool.Domain;
import edu.alibaba.mpc4j.common.tool.MathPreconditions;
import edu.alibaba.mpc4j.common.tool.utils.IntUtils;

import java.util.Set;

/**
 * Basic Frequency Oracle LDP config.
 *
 * @author anonymous author
 * @date 2023/1/14
 */
public class BasicFoLdpConfig implements FoLdpConfig {
    /**
     * the type
     */
    private final FoLdpType type;
    /**
     * the domain
     */
    protected final Domain domain;
    /**
     * the domain size d
     */
    protected final int d;
    /**
     * the privacy parameter ε
     */
    protected final double epsilon;

    protected BasicFoLdpConfig(Builder builder) {
        type = builder.type;
        domain = new Domain(builder.domainSet);
        d = builder.d;
        epsilon = builder.epsilon;
    }

    @Override
    public FoLdpType getType() {
        return type;
    }

    @Override
    public Domain getDomain() {
        return domain;
    }

    @Override
    public int getD() {
        return d;
    }

    @Override
    public double getEpsilon() {
        return epsilon;
    }

    public static class Builder implements org.apache.commons.lang3.builder.Builder<BasicFoLdpConfig> {
        /**
         * the type
         */
        private final FoLdpType type;
        /**
         * the domain set
         */
        private final Set<String> domainSet;
        /**
         * the domain size d
         */
        private final int d;
        /**
         * the privacy parameter ε
         */
        private final double epsilon;

        public Builder(FoLdpType type, Set<String> domainSet, double epsilon) {
            this.type = type;
            d = domainSet.size();
            MathPreconditions.checkPositiveInRange("|Ω|", d, IntUtils.MAX_SIGNED_POWER_OF_TWO);
            MathPreconditions.checkGreater("|Ω|", d, 1);
            this.domainSet = domainSet;
            MathPreconditions.checkPositive("ε", epsilon);
            this.epsilon = epsilon;
        }

        @Override
        public BasicFoLdpConfig build() {
            return new BasicFoLdpConfig(this);
        }
    }
}
