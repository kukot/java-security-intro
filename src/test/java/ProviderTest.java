import org.conscrypt.Conscrypt;
import org.junit.jupiter.api.Test;

import java.security.Security;
import java.util.Arrays;

public class ProviderTest {

    @Test
    public void show_all_providers() {
        var conscryptProvider = Conscrypt.newProviderBuilder();
        Security.addProvider(conscryptProvider.build());
        var svcs = Arrays.stream(Security.getProviders())
                .flatMap((provider) -> provider.getServices().stream())
                .toList();

//        var algos = new TreeSet<String>();
//        svcs.stream().map(Provider.Service::getAlgorithm).forEach(algos::add);
//        algos.forEach(System.out::println);
        svcs.forEach(System.out::println);
    }
}
