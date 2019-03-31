package tcc.fatec.com.br.guiacomercialtcc.client;

import java.util.Arrays;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.fatec.com.br.guiacomercialtcc.util.Constantes;

    public class ClientApi {

    private static Retrofit retrofit;

    public static Api getApi() {
        if (retrofit == null) {
            final String host = Constantes.HOST;

            OkHttpClient httpClient;
            if (host.startsWith("https://")) {
                ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                        .cipherSuites(
                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)
                        .build();
                httpClient = new OkHttpClient().newBuilder().connectionSpecs(Arrays.asList(spec)).build();
            } else {
                httpClient = new OkHttpClient().newBuilder().build();
            }

            retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create()).client(httpClient).build();
        }
        return retrofit.create(Api.class);
    }

}
