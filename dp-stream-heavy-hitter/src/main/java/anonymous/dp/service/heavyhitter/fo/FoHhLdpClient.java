package anonymous.dp.service.heavyhitter.fo;

import anonymous.dp.service.fo.FoLdpClient;
import anonymous.dp.service.fo.FoLdpFactory;
import anonymous.dp.service.fo.config.FoLdpConfig;
import anonymous.dp.service.heavyhitter.AbstractHhLdpClient;
import anonymous.dp.service.heavyhitter.config.FoHhLdpConfig;
import anonymous.dp.service.heavyhitter.utils.EmptyHhLdpServerContext;
import anonymous.dp.service.heavyhitter.utils.HhLdpServerContext;
import com.google.common.base.Preconditions;

import java.util.Random;

/**
 * Abstract Heavy Hitter LDP client based on Frequency Oracle.
 *
 * @author anonymous author
 * @date 2023/1/5
 */
public class FoHhLdpClient extends AbstractHhLdpClient {
    /**
     * Frequency Oracle LDP client
     */
    private final FoLdpClient foLdpClient;

    public FoHhLdpClient(FoHhLdpConfig config) {
        super(config);
        FoLdpConfig foLdpConfig = config.getFoLdpConfig();
        foLdpClient = FoLdpFactory.createClient(foLdpConfig);
    }

    @Override
    public byte[] randomize(HhLdpServerContext serverContext, String item, Random random) {
        Preconditions.checkArgument(serverContext instanceof EmptyHhLdpServerContext);
        return foLdpClient.randomize(item, random);
    }
}
