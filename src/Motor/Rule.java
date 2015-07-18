package Motor;
import java.util.Hashtable;


public class Rule {
    private String name = null;
    private Hashtable conditions = null;
    private Hashtable actions = null;
    private boolean active = true;

    Rule(String name, Hashtable conditions, Hashtable actions) {
        this.name = name;
        this.conditions = conditions;
        this.actions = actions;
    }

    void setInactive() {
        active = false;
    }

    public String getName() {
        return name;
    }

    public Hashtable getConditions() {
        return conditions;
    }

    public Hashtable getActions() {
        return actions;
    }

    public boolean isActive() {
        return active;
    }
} 
