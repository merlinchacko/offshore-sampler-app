package com.fugro.osa.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.fugro.osa.model.Location;
import com.fugro.osa.model.Sample;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

import java.time.LocalDate;
import java.util.List;

public class SampleDialog extends Dialog {
	
	private Sample sample;
	private List<Location> locations;
	private Text unitWeightText;
	private Text waterContentText;
	private Text shearStrengthText;
	private Combo locationCombo;
	private DateTime dateCollectedPicker;

	public SampleDialog(Shell parentShell, Sample sample, List<Location> locations) {
		super(parentShell);
		this.sample = sample;
		this.locations = locations;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));

		// Location Dropdown
		new Label(container, SWT.NONE).setText("Location:");
		locationCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (Location location : locations) {
			locationCombo.add(location.getName());
		}

		// Date Picker
		new Label(container, SWT.NONE).setText("Date Collected:");
		dateCollectedPicker = new DateTime(container, SWT.CALENDAR);

		// Unit Weight
		new Label(container, SWT.NONE).setText("Unit Weight (kN/m3):");
		unitWeightText = new Text(container, SWT.BORDER);

		// Water Content
		new Label(container, SWT.NONE).setText("Water Content (%):");
		waterContentText = new Text(container, SWT.BORDER);

		// Shear Strength
		new Label(container, SWT.NONE).setText("Shear Strength (kPa):");
		shearStrengthText = new Text(container, SWT.BORDER);

		return container;
	}

	@Override
	protected void okPressed() {
		// Set Sample values
		sample.setLocation(locations.get(locationCombo.getSelectionIndex()));
		sample.setDateCollected(LocalDate.of(dateCollectedPicker.getYear(), dateCollectedPicker.getMonth() + 1,
				dateCollectedPicker.getDay()));
		sample.setUnitWeight(Double.parseDouble(unitWeightText.getText()));
		sample.setWaterContent(Double.parseDouble(waterContentText.getText()));
		sample.setShearStrength(Double.parseDouble(shearStrengthText.getText()));

		super.okPressed();
	}
}
