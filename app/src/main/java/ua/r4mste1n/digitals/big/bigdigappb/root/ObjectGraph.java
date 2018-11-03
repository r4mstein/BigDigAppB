package ua.r4mste1n.digitals.big.bigdigappb.root;

import android.content.Context;

import ua.r4mste1n.digitals.big.bigdigappb.root.di.DaggerDiRootComponent;
import ua.r4mste1n.digitals.big.bigdigappb.root.di.DiAppModule;
import ua.r4mste1n.digitals.big.bigdigappb.root.di.DiRootComponent;

/**
 * Created by Alex Shtain on 03.11.2018.
 */
public final class ObjectGraph {
    private static ObjectGraph graph;

    public static ObjectGraph getInstance(final Context _context) {
        if (graph == null) {
            graph = new ObjectGraph(_context);
        }
        return graph;
    }

    public ObjectGraph(final Context _context) {
        DiRootComponent rootComponent = DaggerDiRootComponent.builder().diAppModule(new DiAppModule(_context)).build();
    }
}