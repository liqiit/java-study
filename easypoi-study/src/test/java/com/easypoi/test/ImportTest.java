package com.easypoi.test;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import com.easypoi.pojo.C9CDR;
import com.easypoi.pojo.UnicomCDR;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Title: ImportTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/19
 */
public class ImportTest {
    @Test
    public void importUnicomCdr() {
        File file = new File("E:\\CDR.GS.IF.2020111823300000000.66.txt");
        try {
            CsvImportParams params = new CsvImportParams();
            params.setHeadRows(0);
            params.setSpiltMark(";");
            params.setTextMark("\"");
            //列名包含下划线需要特殊处理

            CsvImportUtil.importCsv(new FileInputStream(file), UnicomCDR.class, params, new IReadHandler() {
                @Override
                public void handler(Object o) {
                    UnicomCDR cdr = (UnicomCDR) o;
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        String json = mapper.writeValueAsString(cdr);
                        System.out.println(json);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void doAfterAll() {
                    System.out.println("game over");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void importC9Cdr() {
        File file = new File("E:\\2020-11-01.csv");
        try {
            CsvImportParams params = new CsvImportParams();
            params.setHeadRows(1);
            params.setSpiltMark(",");
            params.setTextMark("\"");
            //列名包含下划线需要特殊处理

            CsvImportUtil.importCsv(new FileInputStream(file), C9CDR.class, params, new IReadHandler() {
                @Override
                public void handler(Object o) {
                    C9CDR cdr = (C9CDR) o;
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        String json = mapper.writeValueAsString(cdr);
                        System.out.println(json);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void doAfterAll() {
                    System.out.println("game over");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
