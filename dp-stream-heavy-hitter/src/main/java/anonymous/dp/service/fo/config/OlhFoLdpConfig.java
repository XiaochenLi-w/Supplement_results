package anonymous.dp.service.fo.config;

import anonymous.dp.service.fo.FoLdpFactory.FoLdpType;
import edu.alibaba.mpc4j.common.tool.MathPreconditions;

import java.util.Set;

/**
 * Optimal Local Hash (OLH) Frequency Oracle LDP config.
 * The only difference between OLH config and the basic config is that we require a maximum g.
 *
 * @author anonymous author
 * @date 2023/2/2
 */
public class OlhFoLdpConfig extends BasicFoLdpConfig {
    /**
     * we need an int g, therefore, the maximal ε would be ln(MAX_INT) - 1.
     */
    public static final double MAX_EPSILON = Math.log(Integer.MAX_VALUE) - 1;

    private OlhFoLdpConfig(Builder builder) {
        super(builder);
    }

    public static class Builder extends BasicFoLdpConfig.Builder {


        public Builder(FoLdpType type, Set<String> domainSet, double epsilon) {
            super(type, domainSet, epsilon);
            MathPreconditions.checkLessOrEqual("ε", epsilon, MAX_EPSILON);
        }

        @Override
        public OlhFoLdpConfig build() {
            return new OlhFoLdpConfig(this);
        }
    }
}
