package com.fugro.osa.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

import com.fugro.osa.model.Location;
import com.fugro.osa.model.Sample;
import com.fugro.osa.service.BackendService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleListView extends ViewPart {
	public static final String ID = "com.example.rcp.views.sampleListView";

	private TableViewer tableViewer;
	private List<Sample> samples = new ArrayList<>();
	private List<Location> locations = new ArrayList<>();
	private BackendService backendService = new BackendService();

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		loadLocations();
		loadSamples();

		createTableViewer(parent);
		createButtons(parent);
	}

	private void createTableViewer(Composite parent) {
		tableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		// Table setup
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// Columns
		createColumn("Sample ID", 100, "sampleId");
		createColumn("Location", 150, "location");
		createColumn("Date Collected", 120, "dateCollected");
		createColumn("Unit Weight (kN/m³)", 150, "unitWeight");
		createColumn("Water Content (%)", 150, "waterContent");
		createColumn("Shear Strength (kPa)", 150, "shearStrength");

		// Set input
		tableViewer.setInput(samples);
	}

	private void createColumn(String title, int width, String property) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
		column.getColumn().setText(title);
		column.getColumn().setWidth(width);
		column.setLabelProvider(new SampleLabelProvider(property));
	}

	private void createButtons(Composite parent) {
		Composite buttonContainer = new Composite(parent, SWT.NONE);
		buttonContainer.setLayout(new GridLayout(3, false));

		// Add Sample Button
		Button addButton = new Button(buttonContainer, SWT.PUSH);
		addButton.setText("Add Sample");
		addButton.addListener(SWT.Selection, event -> openSampleDialog(null));

		// Edit Sample Button
		Button editButton = new Button(buttonContainer, SWT.PUSH);
		editButton.setText("Edit Sample");
		editButton.addListener(SWT.Selection, event -> {
			IStructuredSelection selection = tableViewer.getStructuredSelection();
			if (!selection.isEmpty()) {
				Sample selectedSample = (Sample) selection.getFirstElement();
				openSampleDialog(selectedSample);
			}
		});

		// Delete Sample Button
		Button deleteButton = new Button(buttonContainer, SWT.PUSH);
		deleteButton.setText("Delete Sample");
		deleteButton.addListener(SWT.Selection, event -> {
			IStructuredSelection selection = tableViewer.getStructuredSelection();
			if (!selection.isEmpty()) {
				Sample selectedSample = (Sample) selection.getFirstElement();
				samples.remove(selectedSample);
				tableViewer.refresh();
			}
		});
	}

	private void openSampleDialog(Sample sample) {
		if (sample == null) {
			sample = new Sample(); // New sample
			samples.add(sample);
		}

		SampleDialog dialog = new SampleDialog(getSite().getShell(), sample, locations);
		if (dialog.open() == Window.OK) {
			tableViewer.refresh();
		} else if (sample.getSampleId() == 0) {
			samples.remove(sample); // Remove sample if it was canceled during creation
		}
	}

	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	private void showErrorDialog(String title, String message) {
		org.eclipse.jface.dialogs.MessageDialog.openError(getSite().getShell(), title, message);
	}

	private void loadSamples() {
		try {
			List<Sample> fetchedSamples = backendService.loadSamples();

			getSite().getShell().getDisplay().asyncExec(() -> {
				samples.clear();
				samples.addAll(fetchedSamples);
				tableViewer.refresh();
			});
		} catch (IOException e) {
			showErrorDialog("Failed to Fetch Data", "Unable to fetch samples from the backend." + e.getMessage());
		}
	}

	private void loadLocations() {
		try {
			List<Location> fetchedLocations = backendService.loadLocations();

			getSite().getShell().getDisplay().asyncExec(() -> {
				locations.clear();
				locations.addAll(fetchedLocations);
				tableViewer.refresh();
			});
		} catch (IOException e) {
			showErrorDialog("Failed to Fetch Data", "Unable to fetch locations from the backend." + e.getMessage());
		}
	}

}