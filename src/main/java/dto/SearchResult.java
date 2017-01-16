package dto;

public class SearchResult {


    public SearchResult(Object record, Object sequence, Object value) {
        this.record = "" + record;
        this.sequence = Integer.valueOf("" + sequence);
        this.value = "" + value;
    }

    public final String record;
    public final Integer sequence;
    public final String value;

}
