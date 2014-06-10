package ge.tsu.server.util;

import org.jboss.logging.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @param <M> Model
 * @param <E> Entity
 * @author nick
 */
public class ModelHelper<M, E> implements Serializable {

    private static Logger log = Logger.getLogger(ModelHelper.class.getClass());

    public M toModel(M m, E e) {
        if (m != null && e != null)
            convert(e, m);
        return m;
    }

    public E toEntity(M m, E e) {
        if (m != null && e != null)
            convert(m, e);
        return e;
    }

    public static void convert(Object source, Object dest) {
        try {
            Class<?> destClass = dest.getClass();
            Class<?> sourceClass = source.getClass();
            for (Field sf : sourceClass.getDeclaredFields()) {
                sf.setAccessible(true);
                Field df = null;

                try {
                    df = destClass.getDeclaredField(sf.getName());
                } catch (NoSuchFieldException ex) {
                    // TODO
                }

                if (df != null) {
                    df.setAccessible(true);
                    Object value = sf.get(source);
                    df.set(dest, value);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
