package getting.prices.api.utils;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import getting.prices.api.domain.product.Product;
import org.springframework.beans.factory.annotation.Value;

public class ConsultationGpt {

    public static final String TEXT_DAVINCI_003 = "text-davinci-003";
    public static final String SELLER_PRODUCT_GPT_TEXT = "Crie um texto como se fosse um vendedor do produto %s.";
    @Value("${token.gpt}")
    public static String token;

    public static String getProductInfo(Product product) {

        OpenAiService service = new OpenAiService(token);

        CompletionRequest request = CompletionRequest.builder()
                .model(TEXT_DAVINCI_003)
                .prompt(String.format(SELLER_PRODUCT_GPT_TEXT, product.getDescription()))
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(request);
        return response.getChoices().get(0).getText();
    }
}
