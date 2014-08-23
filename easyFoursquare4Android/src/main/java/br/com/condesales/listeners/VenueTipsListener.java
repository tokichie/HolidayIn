package br.com.condesales.listeners;

import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.TipsGroup;

/**
 * Created by dionysis_lorentzos on 2/8/14.
 * All rights reserved by the Author.
 * Use with your own responsibility.
 */

public interface VenueTipsListener extends ErrorListener {

    public void onGotVenueTips(TipsGroup tipsGroup);

}
