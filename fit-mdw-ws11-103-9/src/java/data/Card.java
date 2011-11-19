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
public class Card {

    @Id
    private Long id;
    private CardColor type;
    Key<Player> player;

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Key<Player> player) {
        this.player = player;
    }

    public CardColor getType() {
        return type;
    }

    public void setType(CardColor type) {
        this.type = type;
    }

}
