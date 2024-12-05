package br.cupom.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class MercadoPagoService {

    public Preference criarPreferencia(String title, Integer quantity, Double  price ) {

        try {
            MercadoPagoConfig.setAccessToken("APP_USR-93815407785678-071917-f098910beb71717716928d4f93dec081-1909389152");
            PreferenceClient client = new PreferenceClient();
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title(title)
                    .quantity(quantity)
                    .unitPrice(BigDecimal.valueOf(price))
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(item);

            PreferenceRequest request = PreferenceRequest.builder()
                    .items(items)
                    .purpose("wallet_purchase")
                    .build();

            Preference preference = client.create(request);

            return preference;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar preferência no Mercado Pago", e);
        }
    }
}
