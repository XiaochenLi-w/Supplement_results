package anonymous.dp.service.heavyhitter.hg;

import anonymous.dp.service.heavyhitter.AbstractHhLdpClient;
import com.google.common.base.Preconditions;
import anonymous.dp.service.heavyhitter.HhLdpFactory;
import anonymous.dp.service.heavyhitter.config.BgrHgHhLdpConfig;
import anonymous.dp.service.heavyhitter.utils.EmptyHhLdpServerContext;
import anonymous.dp.service.heavyhitter.utils.HhLdpServerContext;
import anonymous.dp.service.tool.BucketDomain;

import java.util.Random;

/**
 * Basic General Randomization HeavyGuardian-based Heavy Hitter LDP client.
 *
 * @author anonymous author
 * @date 2023/1/5
 */
public class BgrHgHhLdpClient extends AbstractHhLdpClient {
    /**
     * the bucket domain
     */
    private final BucketDomain bucketDomain;
    /**
     * p = e^ε / (e^ε + d - 1)
     */
    private final double p;
    /**
     * q = 1 / (e^ε + d - 1)
     */
    private final double q;

    public BgrHgHhLdpClient(BgrHgHhLdpConfig bgrHgHhLdpConfig) {
        super(bgrHgHhLdpConfig);
        int w = bgrHgHhLdpConfig.getW();
        int lambdaH = bgrHgHhLdpConfig.getLambdaH();
        bucketDomain = new BucketDomain(bgrHgHhLdpConfig.getDomainSet(), w, lambdaH);
        double expWindowEpsilon = Math.exp(windowEpsilon);
        p = expWindowEpsilon / (expWindowEpsilon + d - 1);
        q = 1 / (expWindowEpsilon + d - 1);
    }

    @Override
    public byte[] randomize(HhLdpServerContext serverContext, String item, Random random) {
        Preconditions.checkArgument(serverContext instanceof EmptyHhLdpServerContext);
        checkItemInDomain(item);
        // basic HeavyGuardian solution does not consider the current data structure
        double randomSample = random.nextDouble();
        // Randomly sample an integer in [0, d)
        int randomIndex = random.nextInt(d);
        if (randomSample > p - q) {
            // answer a random item
            return bucketDomain.getUniversalIndexItem(randomIndex).getBytes(HhLdpFactory.DEFAULT_CHARSET);
        } else {
            // answer the true item
            return item.getBytes(HhLdpFactory.DEFAULT_CHARSET);
        }
    }
}
