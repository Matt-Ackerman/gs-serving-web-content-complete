package hello;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ca.rmen.rhymer.RhymeResult;
import ca.rmen.rhymer.Rhymer;
import ca.rmen.rhymer.cmu.CmuDictionary;

public class RhymeTest {

	public static void main(String[] args) throws IOException {
		
		Rhymer rhymer = CmuDictionary.loadRhymer();
		
		String word = "battleship";
		List<RhymeResult> results = rhymer.getRhymingWords(word);
		
        for (RhymeResult result : results) {
            System.out.println("Results for " + result.variantNumber + ":");

            System.out.println("  Strict matches:");
            System.out.println("    " + Arrays.toString(result.strictRhymes));
            System.out.println();
            System.out.println("  One-syllable matches:");
            System.out.println("    " + Arrays.toString(result.oneSyllableRhymes));
            System.out.println();
            System.out.println("  Two-syllable matches:");
            System.out.println("    " + Arrays.toString(result.twoSyllableRhymes));
            System.out.println();
            System.out.println("  Three-syllable matches:");
            System.out.println("    " + Arrays.toString(result.threeSyllableRhymes));
        }

	}

}
