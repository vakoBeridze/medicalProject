package ge.tsu.client.view;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import ge.tsu.client.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vako on 12/06/14.
 */
public abstract class CustomGrid<M> {

    private static int INNER_ID = 1;
    private Grid<M> grid;

    public CustomGrid() {
        initGrid();
    }

    private void initGrid() {
        ColumnConfig<M, String> column = new ColumnConfig<M, String>(new ValueProvider<M, String>() {
            @Override
            public String getValue(M m) {
                return getCustomValue(m);
            }

            @Override
            public void setValue(M m, String s) {
            }

            @Override
            public String getPath() {
                return "";
            }
        });
        List<ColumnConfig<M, ?>> columnConfig = new ArrayList<ColumnConfig<M, ?>>();
        columnConfig.add(column);
        ColumnModel<M> cm = new ColumnModel<M>(columnConfig);

        ListStore<M> store = new ListStore<M>(new ModelKeyProvider<M>() {
            @Override
            public String getKey(M m) {
                return getModelKey();
            }
        });

        grid = new Grid<M>(store, cm);
        grid.setHideHeaders(true);
        grid.getView().setEmptyText(App.messages.noData());
        grid.getView().setAutoExpandColumn(column);
        grid.getView().setAutoFill(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        grid.setBorders(false);
    }

    private String getModelKey() {
        return String.valueOf(INNER_ID++);
    }

    protected abstract String getCustomValue(M model);

    public Grid<M> getGrid() {
        return grid;
    }
}
