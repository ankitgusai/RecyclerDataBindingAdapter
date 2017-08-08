package xyz.ankit.recyclerdatabindingadapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.ankit.library.RecyclerDataBindingAdapter;
import xyz.ankit.recyclerdatabindingadapter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements RecyclerDataBindingAdapter.OnClickListener<RowViewModel> {
    private RecyclerDataBindingAdapter<RowViewModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.rvHome.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RecyclerDataBindingAdapter<>(R.layout.row_main, BR.rowData, this);
        mBinding.rvHome.setAdapter(adapter);

        setupListing();
    }

    private void setupListing() {
        List<RowViewModel> viewModels = new ArrayList<>();
        RowViewModel rowViewModel = new RowViewModel();
        rowViewModel.name = "David";
        rowViewModel.age = 24;
        viewModels.add(rowViewModel);

        rowViewModel = new RowViewModel();
        rowViewModel.name = "Jason";
        rowViewModel.age = 38;
        viewModels.add(rowViewModel);

        rowViewModel = new RowViewModel();
        rowViewModel.name = "Todd";
        rowViewModel.age = 23;
        viewModels.add(rowViewModel);

        rowViewModel = new RowViewModel();
        rowViewModel.name = "Alex";
        rowViewModel.age = 25;
        viewModels.add(rowViewModel);

        rowViewModel = new RowViewModel();
        rowViewModel.name = "Liza";
        rowViewModel.age = 22;
        viewModels.add(rowViewModel);

        adapter.setList(viewModels);
        adapter.notifyDataSetChanged();

    }

    //Click appears here
    @Override
    public void onItemClick(int pos, RowViewModel rowViewModel) {
        Toast.makeText(MainActivity.this, "CLicked " + pos, Toast.LENGTH_SHORT).show();
    }
}
