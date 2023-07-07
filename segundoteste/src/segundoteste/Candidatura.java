package segundoteste;

public class Candidatura {
    public Candidatura(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    private int id;
    private String name;
    private String status;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
