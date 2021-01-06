import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadDataImpl implements ReadData {

    private InputStreamReader inputStreamReader;
    private BufferedReader reader;

    public ReadDataImpl() {
        this.setInputStreamReader(new InputStreamReader(System.in));
        this.setReader(new BufferedReader(this.getInputStreamReader()));

    }

    public InputStreamReader getInputStreamReader() {
        return inputStreamReader;
    }

    public void setInputStreamReader(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readData() throws IOException {
        return this.getReader().readLine();
    }

    public String[] getTokens() throws IOException {
        return this.readData().split(" ");
    }
}