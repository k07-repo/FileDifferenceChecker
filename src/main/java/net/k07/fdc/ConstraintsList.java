package net.k07.fdc;

import java.awt.*;

public class ConstraintsList {

    public static final GridBagConstraints folder1Area = new GBCBuilder()
            .gridLocation(0, 0)
            .componentSize(4, 1)
            .internalPadding(0, 0)
            .externalPadding(0, 30, 0, 30)
            .fill(GridBagConstraints.HORIZONTAL)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints folder2Area = new GBCBuilder()
            .gridLocation(0, 1)
            .componentSize(4, 1)
            .internalPadding(0, 0)
            .externalPadding(0, 30, 0, 30)
            .fill(GridBagConstraints.HORIZONTAL)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints folder1Button = new GBCBuilder()
            .gridLocation(4, 0)
            .componentSize(2, 1)
            .internalPadding(0, 0)
            .externalPadding(0, 30, 0, 30)
            .fill(GridBagConstraints.HORIZONTAL)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints folder2Button = new GBCBuilder()
            .gridLocation(4, 1)
            .componentSize(2, 1)
            .internalPadding(0, 0)
            .externalPadding(0, 30, 0, 30)
            .fill(GridBagConstraints.HORIZONTAL)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints changedFilesPanel = new GBCBuilder()
            .gridLocation(0, 2)
            .componentSize(2, 2)
            .internalPadding(0, 0)
            .externalPadding(0, 0, 0, 0)
            .fill(GridBagConstraints.BOTH)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints folder1Panel = new GBCBuilder()
            .gridLocation(2, 2)
            .componentSize(2, 2)
            .internalPadding(0, 0)
            .externalPadding(0, 0, 0, 0)
            .fill(GridBagConstraints.BOTH)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints folder2Panel = new GBCBuilder()
            .gridLocation(4, 2)
            .componentSize(2, 2)
            .internalPadding(0, 0)
            .externalPadding(0, 0, 0, 0)
            .fill(GridBagConstraints.BOTH)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

    public static final GridBagConstraints startButton = new GBCBuilder()
            .gridLocation(0, 4)
            .componentSize(6, 1)
            .internalPadding(0, 0)
            .externalPadding(0, 0, 0, 0)
            .fill(GridBagConstraints.HORIZONTAL)
            .anchor(GridBagConstraints.CENTER)
            .weight(0.5, 0.5)
            .build();

}
