package com.example.manu.cinemaappv2.comming;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.manu.cinemaappv2.R;
import com.example.manu.cinemaappv2.constants.G;
import com.example.manu.cinemaappv2.ppv.PpvInsertActivity;
import com.example.manu.cinemaappv2.ppv.PpvModifyActivity;
import com.example.manu.cinemaappv2.proveedor.Contrato;
import com.example.manu.cinemaappv2.proveedor.PpvProveedor;

public class CommingListFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    //private static final String LOGTAG = "Tiburcio - CommingListFragment";

    PPVCursorAdapter mAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    ActionMode mActionMode;
    View viewSelected;
    int ppvId;
    Intent intent;
    public static CommingListFragment newInstance() {
        CommingListFragment f = new CommingListFragment();

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuItem menuItem = menu.add(Menu.NONE, G.INSERT, Menu.NONE, "Insert");
        menuItem.setIcon(R.drawable.ic_action_anadir);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case G.INSERT:
            Intent intent = new Intent(getActivity(),PpvInsertActivity.class);
            startActivity(intent);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.i(LOGTAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_ppv_list, container, false);

        mAdapter = new PPVCursorAdapter(getActivity());
        setListAdapter(mAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i(LOGTAG, "onActivityCreated");

        mCallbacks = this;

        getLoaderManager().initLoader(0, null, mCallbacks);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(mActionMode!=null){
                    return false;
                }
                mActionMode = getActivity().startActionMode(mActionModeCallback);
                view.setSelected(true);
                viewSelected =view;
                return true;
            }
        });
    }

    ActionMode.Callback mActionModeCallback =new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_delete:
                    ppvId = (Integer) viewSelected.getTag();
                    PpvProveedor.deleteRecord(getActivity().getContentResolver(),ppvId);
                    mActionMode.finish();
                    intent =new Intent(getActivity(), CommingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_edit:
                    ppvId = (Integer) viewSelected.getTag();
                    intent = new Intent(getActivity(),PpvModifyActivity.class);
                    intent.putExtra(Contrato.PPV._ID, ppvId);
                    startActivity(intent);
                    mActionMode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode =null;
        }
    };

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.  This
        // sample only has one Loader, so we don't care about the ID.
        // First, pick the base URI to use depending on whether we are
        // currently filtering.
        String columns[] = new String[]{Contrato.PPV._ID,
                                        Contrato.PPV.TITLE,
                                        Contrato.PPV.DIRECTOR,
                                        Contrato.PPV.GUIONIST,
                                        Contrato.PPV.YEAR,
                                        Contrato.PPV.CATEGORY

        };

        Uri baseUri = Uri.parse("content://" + Contrato.AUTHORITY + "/ppvFilms/PPVS");

        Log.i("tiburcio", "tomar por saco");

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.

        String selection = "";

        return new CursorLoader(getActivity(), baseUri,
                columns, selection, null, null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)

        Uri laUriBase = Uri.parse("content://" + Contrato.AUTHORITY + "/ppvFilms/kakita");
        data.setNotificationUri(getActivity().getContentResolver(), laUriBase);

        mAdapter.swapCursor(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    public class PPVCursorAdapter extends CursorAdapter {
        public PPVCursorAdapter(Context context) {
            super(context, null, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            int ID = cursor.getInt(cursor.getColumnIndex(Contrato.PPV._ID));
            String title = cursor.getString(cursor.getColumnIndex(Contrato.PPV.TITLE));
            String director = cursor.getString(cursor.getColumnIndex(Contrato.PPV.DIRECTOR));
            String guionist = cursor.getString(cursor.getColumnIndex(Contrato.PPV.GUIONIST));
            int year = cursor.getInt(cursor.getColumnIndex(Contrato.PPV.YEAR));


            TextView textviewTitle = (TextView) view.findViewById(R.id.textview_title);
            textviewTitle.setText(title);

            TextView textviewDirector = (TextView) view.findViewById(R.id.textview_director);
            textviewDirector.setText(director);

            TextView textviewGuionist = (TextView) view.findViewById(R.id.textview_guionist);
            textviewGuionist.setText(guionist);

            TextView textviewYear = (TextView) view.findViewById(R.id.textview_year);
            Log.i("tiburcio", "hola: " + year);
            textviewYear.setText(String.valueOf(year));


            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
            int color = generator.getColor(director); //Genera un color según el nombre
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(title.substring(0, 1), color);

            ImageView image = (ImageView) view.findViewById(R.id.image_view);
            image.setImageDrawable(drawable);

            view.setTag(ID);

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.ppv_list_item, parent, false);
            bindView(v, context, cursor);
            return v;
        }
    }
}
