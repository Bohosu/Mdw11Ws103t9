/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ondrej
 */
abstract class DAO<T> extends DAOBase {

    private Class<T> c;

    public DAO(Class<T> c) {
        this.c = c;
    }
    
    public Key<T> put(T e) {
        return ofy().put(e);
    }

    public Query<T> query() {
        return ofy().query(c);
    }

    public T get(Long id) {
        return ofy().get(c, id);
    }

    public T get(String id) {
        return ofy().get(c, id);
    }

    public Map<Key<T>, T> get(Iterable<Key<T>> itrbl) {
        return ofy().get(itrbl);
    }

    public List<T> getAll() {
        return ofy().query(c).list();
    }

    public Map<Key<T>, T> getAllFetched() {
        return ofy().get(ofy().query(c).listKeys());
    }

    public void delete(T e) {
        ofy().delete(e);
    }

    public void delete(Iterable<Key<T>> itrbl) {
        ofy().delete(itrbl);
    }

    public void delete(Long id) {
        ofy().delete(c, id);
    }

    public void delete(String id) {
        ofy().delete(c, id);
    }
}