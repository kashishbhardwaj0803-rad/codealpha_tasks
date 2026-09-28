import java.util.HashMap;
import java.util.Scanner;

public class AIChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> responses = new HashMap<>();

        // Chatbot Knowledge Base (Rules)
        responses.put("hello", "Hello! How can I help you today?");
        responses.put("hi", "Hi there! What can I do for you?");
        responses.put("how are you", "I'm just a bot, but I'm doing great! How about you?");
        responses.put("what is your name", "I am AlphaBot, your virtual assistant.");
        responses.put("help", "Sure! I can answer basic questions or chat with you. Try asking my name.");
        responses.put("bye", "Goodbye! Have a wonderful day ahead!");

        System.out.println("=== AI Chatbot (AlphaBot) Initialized ===");
        System.out.println("Type 'bye' to exit the chat.\n");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("bye")) {
                System.out.println("Bot: " + responses.get("bye"));
                break;
            }

            String botReply = responses.getOrDefault(userInput, 
                "I'm sorry, I don't understand that. Try asking 'help' to see what I can do!");
            
            System.out.println("Bot: " + botReply + "\n");
        }
        scanner.close();
    }
}