package com.fugro.osa.view;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.fugro.osa.model.Sample;

public class SampleLabelProvider extends ColumnLabelProvider {

	private String column;

	public SampleLabelProvider(String column) {
		this.column = column;
	}

	@Override
	public String getText(Object element) {
		Sample sample = (Sample) element;

		switch (column) {
		case "sampleId":
			return String.valueOf(sample.getSampleId());
		case "location":
			return sample.getLocation().getName();
		case "dateCollected":
			return sample.getDateCollected().toString();
		case "unitWeight":
			return String.valueOf(sample.getUnitWeight());
		case "waterContent":
			return String.valueOf(sample.getWaterContent());
		case "shearStrength":
			return String.valueOf(sample.getShearStrength());
		default:
			return super.getText(element);
		}
	}
}