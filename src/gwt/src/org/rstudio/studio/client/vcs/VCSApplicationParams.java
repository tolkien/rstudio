/*
 * VCSApplicationParams.java
 *
 * Copyright (C) 2009-11 by RStudio, Inc.
 *
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.vcs;

import java.util.ArrayList;

import org.rstudio.studio.client.common.vcs.StatusAndPath;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import org.rstudio.studio.client.common.vcs.StatusAndPathInfo;

public class VCSApplicationParams extends JavaScriptObject
{
   protected VCSApplicationParams()
   {
   }
   
   public final static VCSApplicationParams create(
                                          boolean showHistory,
                                          ArrayList<StatusAndPath> selected)
   {
      JsArray<StatusAndPathInfo> jsSelected =
            JavaScriptObject.createArray().cast();
      
      jsSelected.setLength(selected.size());
      
      for (int i=0; i<selected.size(); i++)
         jsSelected.set(i, selected.get(i).toInfo());
      
      return createNative(showHistory, jsSelected);
   }
   
   private final static native VCSApplicationParams createNative(
                                       boolean showHistory,
                                       JsArray<StatusAndPathInfo> selected) /*-{
      var params = new Object();
      params.show_history = showHistory;
      params.selected = selected;
      return params;
   }-*/; 
   
  
   
   public final native boolean getShowHistory() /*-{
      return this.show_history;
   }-*/;

   public final ArrayList<StatusAndPath> getSelected()
   {
      return StatusAndPath.fromInfos(getSelectedNative());
   }
   
   private final native JsArray<StatusAndPathInfo> getSelectedNative() /*-{
      return this.selected;
   }-*/;


}
