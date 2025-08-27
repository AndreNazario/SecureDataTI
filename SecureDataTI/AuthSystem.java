import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthSystem {
    private List<User> users = new ArrayList<>();
    private List<Document> documents = new ArrayList<>();
    private int nextDocId = 1;
    private final String DATA_FILE = "documents.txt";

    public AuthSystem() {
        // Usuários pré-cadastrados
        users.add(new User("admin", "1234", "Admin"));
        users.add(new User("editor", "abcd", "Editor"));
        users.add(new User("reader", "xyz", "Leitor"));
        
        loadDocuments();
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getNome().equals(username) && user.getSenha().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void showMenu(User user, Scanner sc) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu " + user.getLevel() + " ===");
            
            switch (user.getLevel()) {
                case "Admin":
                    System.out.println("1. Criar documento");
                    System.out.println("2. Editar documento");
                    System.out.println("3. Excluir documento");
                    System.out.println("4. Visualizar documentos");
                    System.out.println("5. Ver documento completo");
                    System.out.println("0. Sair");
                    break;

                case "Editor":
                    System.out.println("1. Editar documento");
                    System.out.println("2. Visualizar documentos");
                    System.out.println("3. Ver documento completo");
                    System.out.println("0. Sair");
                    break;

                case "Leitor":
                    System.out.println("1. Visualizar documentos");
                    System.out.println("2. Ver documento completo");
                    System.out.println("0. Sair");
                    break;
            }

            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

        switch (user.getLevel()) {
                case "Admin":
                    switch (opcao) {
                        case 1 -> createDocument(sc, user.getNome());
                        case 2 -> editDocument(sc);
                        case 3 -> deleteDocument(sc);
                        case 4 -> listDocuments();
                        case 5 -> viewDocument(sc);
                        case 0 -> running = false;
                        default -> System.out.println("Opção inválida!");
                    }
                    break;

                case "Editor":
                    switch (opcao) {
                        case 1 -> editDocument(sc);
                        case 2 -> listDocuments();
                        case 3 -> viewDocument(sc);
                        case 0 -> running = false;
                        default -> System.out.println("Opção inválida!");
                    }
                    break;

                case "Leitor":
                    switch (opcao) {
                        case 1 -> listDocuments();
                        case 2 -> viewDocument(sc);
                        case 0 -> running = false;
                        default -> System.out.println("Opção inválida!");
                    }
                    break;
            }
        }
    }

    private void createDocument(Scanner sc, String author) {
        System.out.print("Título do documento: ");
        String title = sc.nextLine();
        System.out.print("Conteúdo do documento: ");
        String content = sc.nextLine();
        
        String id = "DOC" + String.format("%03d", nextDocId++);
        documents.add(new Document(id, title, content, author));
        saveDocuments();
        System.out.println("Documento criado com sucesso! ID: " + id);
    }

    private void editDocument(Scanner sc) {
        listDocuments();
        System.out.print("Digite o ID do documento para editar: ");
        String id = sc.nextLine();
        
        Document doc = findDocumentById(id);
        if (doc == null) {
            System.out.println("Documento não encontrado!");
            return;
        }
        
        System.out.println("1. Editar título");
        System.out.println("2. Editar conteúdo");
        System.out.print("Escolha: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Novo título: ");
                doc.setTitle(sc.nextLine());
                saveDocuments();
                System.out.println("Título atualizado!");
                break;
            case 2:
                System.out.print("Novo conteúdo: ");
                doc.setContent(sc.nextLine());
                saveDocuments();
                System.out.println("Conteúdo atualizado!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void deleteDocument(Scanner sc) {
        listDocuments();
        System.out.print("Digite o ID do documento para excluir: ");
        String id = sc.nextLine();
        
        Document doc = findDocumentById(id);
        if (doc == null) {
            System.out.println("Documento não encontrado!");
            return;
        }
        
        documents.remove(doc);
        saveDocuments();
        System.out.println("Documento excluído com sucesso!");
    }

    private void listDocuments() {
        if (documents.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
            return;
        }
        
        System.out.println("\n=== DOCUMENTOS ===");
        for (Document doc : documents) {
            System.out.println(doc.getFormattedInfo());
        }
    }

    private void viewDocument(Scanner sc) {
        listDocuments();
        System.out.print("Digite o ID do documento para visualizar: ");
        String id = sc.nextLine();
        
        Document doc = findDocumentById(id);
        if (doc == null) {
            System.out.println("Documento não encontrado!");
            return;
        }
        
        System.out.println("\n" + doc.getFullInfo());
    }

    private Document findDocumentById(String id) {
        for (Document doc : documents) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    private void saveDocuments() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println(nextDocId);
            for (Document doc : documents) {
                writer.println(doc.getId() + "|" + doc.getTitle() + "|" + doc.getContent() + "|" + doc.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar documentos: " + e.getMessage());
        }
    }

    private void loadDocuments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                nextDocId = Integer.parseInt(line);
            }
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4);
                if (parts.length == 4) {
                    documents.add(new Document(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda, criar documentos padrão
            documents.add(new Document("DOC001", "Manual do Sistema", "Este é o manual completo do sistema...", "admin"));
            documents.add(new Document("DOC002", "Relatório Mensal", "Relatório de atividades do mês...", "editor"));
            documents.add(new Document("DOC003", "Política de Segurança", "Diretrizes de segurança da empresa...", "admin"));
            nextDocId = 4;
            saveDocuments();
        } catch (IOException e) {
            System.out.println("Erro ao carregar documentos: " + e.getMessage());
        }
    }
}