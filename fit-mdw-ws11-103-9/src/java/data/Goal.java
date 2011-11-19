/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.googlecode.objectify.Key;
import javax.persistence.Id;

/**
 *
 * @author ondrej
 */
public class Goal {

    @Id
    private Long id;
    private int minute;
    Key<Player> scorer;
    Key<Player> asistent;

    public Goal() {}

    public Key<Player> getAsistent() {
        return asistent;
    }

    public void setAsistent(Key<Player> asistent) {
        this.asistent = asistent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Key<Player> getScorer() {
        return scorer;
    }

    public void setScorer(Key<Player> scorer) {
        this.scorer = scorer;
    }
    
}
