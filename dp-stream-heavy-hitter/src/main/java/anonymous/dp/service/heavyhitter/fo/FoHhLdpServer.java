package anonymous.dp.service.heavyhitter.fo;

import anonymous.dp.service.tool.Domain;
import anonymous.dp.service.fo.FoLdpFactory;
import anonymous.dp.service.fo.FoLdpServer;
import anonymous.dp.service.fo.config.FoLdpConfig;
import anonymous.dp.service.heavyhitter.AbstractHhLdpServer;
import anonymous.dp.service.heavyhitter.HhLdpFactory;
import anonymous.dp.service.heavyhitter.HhLdpServerState;
import anonymous.dp.service.heavyhitter.config.FoHhLdpConfig;
import anonymous.dp.service.heavyhitter.utils.EmptyHhLdpServerContext;
import anonymous.dp.service.heavyhitter.utils.HhLdpServerContext;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Heavy Hitter LDP server based on Frequency Oracle.
 *
 * @author anonymous author
 * @date 2023/1/4
 */
public class FoHhLdpServer extends AbstractHhLdpServer {
    /**
     * the domain
     */
    private final Domain domain;
    /**
     * frequencies in the warmup state
     */
    private final int[] warmupFrequencies;
    /**
     * Frequency Oracle LDP server
     */
    private final FoLdpServer foLdpServer;
    /**
     * the number of inserted items
     */
    private int num;

    public FoHhLdpServer(FoHhLdpConfig config) {
        super(config);
        FoLdpConfig foLdpConfig = config.getFoLdpConfig();
        domain = foLdpConfig.getDomain();
        foLdpServer = FoLdpFactory.createServer(config.getFoLdpConfig());
        warmupFrequencies = new int[config.getD()];
        num = 0;
        hhLdpServerState = HhLdpServerState.WARMUP;
    }

    @Override
    public boolean warmupInsert(byte[] itemBytes) {
        checkState(HhLdpServerState.WARMUP);
        String item = new String(itemBytes, HhLdpFactory.DEFAULT_CHARSET);
        int itemIndex = domain.getItemIndex(item);
        num++;
        warmupFrequencies[itemIndex]++;
        return true;
    }

    @Override
    public void stopWarmup() {
        checkState(HhLdpServerState.WARMUP);
        hhLdpServerState = HhLdpServerState.STATISTICS;
    }

    @Override
    public HhLdpServerContext getServerContext() {
        return new EmptyHhLdpServerContext();
    }

    @Override
    public boolean randomizeInsert(byte[] itemBytes) {
        checkState(HhLdpServerState.STATISTICS);
        foLdpServer.insert(itemBytes);
        num++;
        return true;
    }

    @Override
    public Map<String, Double> heavyHitters() {
        if (num < k) {
            // if the submitted num is less than k, return an empty heavy hitter
            return new HashMap<>(0);
        }
        Map<String, Double> frequencyEstimates = foLdpServer.estimate();
        // add warmups
        for (String item : domain.getDomainSet()) {
            int itemIndex = domain.getItemIndex(item);
            frequencyEstimates.put(item, frequencyEstimates.get(item) + warmupFrequencies[itemIndex]);
        }
        List<Map.Entry<String, Double>> countList = new ArrayList<>(frequencyEstimates.entrySet());
        // descending sort
        countList.sort(Comparator.comparingDouble(Map.Entry::getValue));
        Collections.reverse(countList);
        if (frequencyEstimates.keySet().size() <= k) {
            // the current key set is less than k, return all items
            return countList.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } else {
            return countList
                .subList(0, k)
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    @Override
    public int getNum() {
        return num;
    }
}
