package com.horizonlabs.marketpulse;


import android.view.View;

import com.horizonlabs.marketpulse.ui.activity.MainActivity;
import com.horizonlabs.marketpulse.ui.adapters.ScanAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.filters.MediumTest;

import androidx.test.runner.AndroidJUnit4;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureListViewIsPresent() throws Exception {
        MainActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.rvScanHome);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        ScanAdapter adapter = (ScanAdapter) recyclerView.getAdapter();
        assertThat(adapter, instanceOf(ScanAdapter.class));
        assertThat(adapter.getItemCount(), greaterThan(1));

    }

}