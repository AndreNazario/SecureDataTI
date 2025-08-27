import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Document {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    
    public Document(String id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    
    public void setTitle(String title) { 
        this.title = title; 
        this.lastModified = LocalDateTime.now();
    }
    
    public void setContent(String content) { 
        this.content = content; 
        this.lastModified = LocalDateTime.now();
    }
    
    public String getFormattedInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("ID: %s | Título: %s | Autor: %s | Criado: %s", 
                           id, title, author, createdAt.format(formatter));
    }
    
    public String getFullInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("=== DOCUMENTO ===\nID: %s\nTítulo: %s\nAutor: %s\nCriado: %s\nÚltima modificação: %s\n\nConteúdo:\n%s", 
                           id, title, author, createdAt.format(formatter), lastModified.format(formatter), content);
    }
}