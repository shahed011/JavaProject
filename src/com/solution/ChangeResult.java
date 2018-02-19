package com.solution;

import java.util.List;

public class ChangeResult {
    private boolean _success;
    private List<Double> _changes;

    public boolean is_success() {
        return _success;
    }

    public List<Double> get_changes() {
        return _changes;
    }

    public ChangeResult (boolean success, List<Double> changes) {
        _success = success;
        _changes = changes;
    }
}
