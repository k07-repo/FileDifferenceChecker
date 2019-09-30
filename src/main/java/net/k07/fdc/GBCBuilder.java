package net.k07.fdc;

import java.awt.*;

public class GBCBuilder {
    private GridBagConstraints result;

    public GBCBuilder() {
        result = new GridBagConstraints();
    }

    public GBCBuilder gridLocation(int gridx, int gridy) {
        result.gridx = gridx;
        result.gridy = gridy;
        return this;
    }

    public GBCBuilder componentSize(int gridwidth, int gridheight) {
        result.gridwidth = gridwidth;
        result.gridheight = gridheight;
        return this;
    }

    public GBCBuilder internalPadding(int ipadx, int ipady) {
        result.ipadx = ipadx;
        result.ipady = ipady;
        return this;
    }

    public GBCBuilder externalPadding(int top, int left, int bottom, int right) {
        result.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GBCBuilder fill(int value) {
        result.fill = value;
        return this;
    }

    public GBCBuilder anchor(int location) {
        result.anchor = location;
        return this;
    }

    public GBCBuilder weight(double weightx, double weighty) {
        result.weightx = weightx;
        result.weighty = weighty;
        return this;
    }

    public GridBagConstraints build() {
        return result;
    }
}
