//package Configuration;
//
//import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
//import org.openqa.grid.web.Hub;
//import org.testng.annotations.BeforeSuite;
//
//import java.util.concurrent.TimeUnit;
//
//public class GridHub  {
//
//    @BeforeSuite
//    public void setUp () throws Exception {
//        GridHubConfiguration config = new GridHubConfiguration();
//        Hub hub = new Hub(config);
//        hub.start();
//        TimeUnit.SECONDS.sleep(10);
//    }
//}