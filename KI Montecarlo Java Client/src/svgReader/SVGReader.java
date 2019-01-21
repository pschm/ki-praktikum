package svgReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SVGReader {
    private File file;

    //String of scanned svg
    private String svgMap;

    //ArrayList with all lines from the svg
    private ArrayList<Line> lines;

    //Arrays which show the placements of houses on the map
    private boolean[] upperRow;
    private boolean[] underRow;


    public SVGReader(String path) {
        this.file = new File(path);
        this.svgMap = readSVG(this.file);
        this.lines = parseSVGString(svgMap);

        this.upperRow = buildUpperRow(this.lines);
        this.underRow = buildUnderRow(this.lines);
    }

    /**
     * Reads the given svg and turns it into a string
     *
     * @param file with the path to the svg
     * @return svg as a string
     */
    private String readSVG(File file) {
        String svgMap = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                svgMap += line;
            }
        } catch (IOException e) {
            System.out.println("IOException while trying to read SVG");
            e.printStackTrace();
        }

        return svgMap;
    }

    /**
     * Parses a String of a svg and puts all lines into an ArrayList
     *
     * @param svgMap String of a svg
     * @return ArrayList with all Lines
     */
    private ArrayList<Line> parseSVGString(String svgMap) {
        ArrayList<Line> lines = new ArrayList<>();
        Document doc = Jsoup.parse(svgMap, "", Parser.xmlParser());

        //Put all lines into an ArrayList
        for (Element e : doc.select("line")) {

            int y1, x1, y2, x2;
            y1 = Integer.parseInt(e.attr("y1").replaceAll("px", ""));
            x1 = Integer.parseInt(e.attr("x1").replaceAll("px", ""));
            y2 = Integer.parseInt(e.attr("y2").replaceAll("px", ""));
            x2 = Integer.parseInt(e.attr("x2").replaceAll("px", ""));

            Line line = new Line(y1, x1, y2, x2);
            lines.add(line);
        }
        return lines;
    }

    /**
     * Iterates over lines in the ArrayList and builds an Array that represents the row above the street
     *
     * @param lines Arraylist with all lines
     * @return Array that represents the row above the street
     */
    private boolean[] buildUpperRow(ArrayList<Line> lines) {
        boolean[] upperRow = new boolean[12];
        Arrays.fill(upperRow, true);

        for (int i = 0; i < upperRow.length; i++) {
            Line myLine = new Line(0, i * 50, 0, i * 50 + 50);
            for (Line l : lines) {
                if (l.containsHorizontalLine(myLine)) {
                    upperRow[i] = false;
                    break;
                }
            }
        }

        return upperRow;
    }

    /**
     * Iterates over lines in the ArrayList and builds an Array that represents the row under the street
     *
     * @param lines Arraylist with all lines
     * @return Array that represents the row under the street
     */
    private boolean[] buildUnderRow(ArrayList<Line> lines) {
        boolean[] underRow = new boolean[12];
        Arrays.fill(underRow, true);
        for (int i = 0; i < underRow.length; i++) {
            Line myLine = new Line(150, i * 50, 150, i * 50 + 50);
            for (Line l : lines) {
                if (l.containsHorizontalLine(myLine)) {
                    underRow[i] = false;
                    break;
                }
            }
        }

        return underRow;
    }

    public boolean[] getUpperRow() {
        return upperRow;
    }

    public boolean[] getUnderRow() {
        return underRow;
    }
}
