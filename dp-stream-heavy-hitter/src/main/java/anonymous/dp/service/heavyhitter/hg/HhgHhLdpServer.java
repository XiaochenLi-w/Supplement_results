package anonymous.dp.service.heavyhitter.hg;

import anonymous.dp.service.heavyhitter.HhLdpServer;

/**
 * Hot Heavy Hitter LDP server.
 *
 * @author anonymous author
 * @date 2023/3/21
 */
public interface HhgHhLdpServer extends HhLdpServer {
    /**
     * Gets γ_h.
     *
     * @return γ_h.
     */
    double getGammaH();
}
