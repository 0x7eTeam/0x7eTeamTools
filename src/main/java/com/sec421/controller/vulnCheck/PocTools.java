package com.sec421.controller.vulnCheck;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.sec421.core.Constants;

import java.util.List;

import static com.sec421.tools.Tools.findPyFiles;

public class PocTools {

    public static String[] getpyFiles(){
        List<String> pyFiles = findPyFiles(Constants.POCPATH);
        String[] pyFileArray = pyFiles.toArray(new String[0]);
        return pyFileArray;

    }

}
