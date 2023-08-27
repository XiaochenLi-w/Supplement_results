package anonymous.dp.service.heavyhitter;

/**
 * Heavy Hitter LDP server state.
 *
 * @author anonymous author
 * @date 2022/11/21
 */
public enum HhLdpServerState {
    /**
     * warm-up state
     */
    WARMUP,
    /**
     * statistics state
     */
    STATISTICS,
}
