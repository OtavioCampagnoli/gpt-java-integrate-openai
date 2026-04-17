package br.com.ocampagnoli;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class IntegrationTest {
    public static void main(String[] args) {
        var prompt = "Gere 5 produtos";
        var system = "Você é um gerador de produtos ficticios para um ecommerce e deve gerar apenas nomes dos produtos.";

        var apiKey = System.getenv("OPEN_IA_API_KEY");

        OpenAiService service = new OpenAiService(apiKey);
        var completionRequest = ChatCompletionRequest.builder()
                .messages(Arrays.asList(
                        new ChatMessage(ChatMessageRole.USER.value(), prompt),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), system)
                ))
                .model("babbage-002")
                .build();
        service.createChatCompletion(completionRequest).getChoices()
                .forEach(r ->
                        completionRequest
                                .getMessages()
                                .forEach(c -> System.out.println(c.getContent())
                        )
        );
    }
}
