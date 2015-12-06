package is.uncommon.droidcon2015.utils;

/**
 * Created by Madhu on 05/12/15.
 */
public class MathUtils {
    //see formula --> http://en.wikipedia.org/wiki/B%C3%A9zier_curve

    /**
     * @param p0 starting point
     * @param p1 control point
     * @param p2 final point
     * @param t time or animated value
     * @return
     */
    public static double quadBezierValue(float p0, float p1, float p2, float t) {
        return (Math.pow((1 - t), 2) * p0)
                + (2 * (1 - t) * t * p1)
                + (Math.pow(t, 2) * p2);
    }
}
