package performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.sentence.utils.StringUtils;
import org.sentence.validation.SentenceValidator;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class SentenceValidatorPerformanceTest {
    private SentenceValidator sentenceValidator;

    @Setup
    public void setup(){
        sentenceValidator= new SentenceValidator();
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void validateCapitalLetters_ReturnsTrueIfSentenceStartsWithCapitalLetter_AverageTime(){
        String test = "This sentence is used to test if sentence starts with capital letter";
        char[] sentence = StringUtils.convertSentenceToCharArray(test);
        sentenceValidator.validateStringStartsWithCapitalLetter(sentence);
    }
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void validateCapitalLetters_ReturnsTrueIfSentenceStartsWithCapitalLetter_Throughput(){
        String test = "This sentence is used to test if sentence starts with capital letter";
        char[] sentence = StringUtils.convertSentenceToCharArray(test);
        sentenceValidator.validateStringStartsWithCapitalLetter(sentence);
    }
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void validateCapitalLetters_ReturnsTrueIfSentenceStartsWithCapitalLetter_SampleTime(){
        String test = "This sentence is used to test if sentence starts with capital letter";
        char[] sentence = StringUtils.convertSentenceToCharArray(test);
        sentenceValidator.validateStringStartsWithCapitalLetter(sentence);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SentenceValidatorPerformanceTest.class.getSimpleName())
                .timeUnit(TimeUnit.NANOSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .measurementTime(TimeValue.microseconds(2))
                .measurementIterations(6)
                .warmupIterations(2)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .forks(1)
                .resultFormat(ResultFormatType.JSON)
                .result("target/generated-test-sources/" + SentenceValidatorPerformanceTest.class.getSimpleName())
                .build();

        new Runner(options).run();
    }
}
