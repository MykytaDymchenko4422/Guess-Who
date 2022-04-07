package practice.ex.guess_who;

public class UserResult {
    private String name;
    private int result;

    @Override
    public String toString() {
        return name + '\n' + result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UserResult(String name, int result) {
        this.name = name;
        this.result = result;
    }
}
