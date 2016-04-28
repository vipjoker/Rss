package com.budivnictvo.rssnews.app.html;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 05.01.2015.
 */
public class HtmlHelper {
    TagNode rootNode;

    public HtmlHelper(String _link) throws IOException{
        HtmlCleaner cleaner = new HtmlCleaner();
        rootNode = cleaner.clean(new URL(_link));
    }
    public List<TagNode> getLinksByTag(String _tag){
        List<TagNode> linkList = new ArrayList<TagNode>();
        TagNode[] linkElements = rootNode.getElementsByName(_tag ,true);
        for (int i = 0 ; i <linkElements.length ; i++ ){
            linkList.add(linkElements[i]);
        }
        return  linkList;
    }

}
