package dto;

/**
 * Created by david on 13/01/17.
 */
public class DiffResult {


    public DiffResult(Integer sequence, String field, String first, String second) {
        this.sequence = sequence;
        this.field = field;
        this.first = first;
        this.second = second;
    }

    public final Integer sequence;
    public final String field;
    public final String first;
    public final String second;
}
