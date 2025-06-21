import com.Tuple;
import org.junit.jupiter.api.*;

public class TupleTests {

    @Test
    @Tag("CreationTest")
    @DisplayName("A tuple with w=1.0 should be a point")
    void tuple_with_w_1_is_a_point() {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1, 1.0);
        Assertions.assertEquals(Tuple.POINT, tuple.getW());
        Assertions.assertTrue(tuple.isPoint());
        Assertions.assertFalse(tuple.isVector());
    }

    @Test
    @Tag("CreationTest")
    @DisplayName("A tuple with w=0.0 should be a vector")
    void tuple_with_w_0_is_a_vector() {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1, 0.0);
        Assertions.assertEquals(Tuple.VECTOR, tuple.getW());
        Assertions.assertTrue(tuple.isVector());
        Assertions.assertFalse(tuple.isPoint());
    }

    @Test
    @Tag("CreationTest")
    @DisplayName("Tuple.createTuple() should create a tuple with w=1.0")
    void createTuple_creates_tuples_with_w_1() {
        Tuple tuple = Tuple.createPoint(1.0, 2.0, 3.0);
        Assertions.assertEquals(Tuple.POINT, tuple.getW());
        Assertions.assertTrue(tuple.isPoint());
    }

    @Test
    @Tag("CreationTest")
    @DisplayName("Tuple.createVector() should create a tuple with w=0.0")
    void createVector_creates_tuples_with_w_0() {
        Tuple tuple = Tuple.createVector(1.0, 2.0, 3.0);
        Assertions.assertEquals(Tuple.VECTOR, tuple.getW());
        Assertions.assertTrue(tuple.isVector());
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("equals() should return true when two tuples are the same")
    void equals_two_tuples_are_equal() {
        Tuple fstTuple = Tuple.createPoint(1.0, 2.0, 3.0);
        Tuple scdTuple = Tuple.createPoint(1.0, 2.0, 3.0);
        Assertions.assertEquals(fstTuple, scdTuple);
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("equals() should return false when two tuples are not the same")
    void equals_two_tuples_are_not_equal() {
        Tuple fstTuple = Tuple.createPoint(1.0, 2.0, 3.0);
        Tuple scdTuple = Tuple.createVector(1.0, 2.0, 3.0);
        Assertions.assertNotEquals(fstTuple, scdTuple);
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("Adding a point to a vector should give a point")
    void tuple_add_point_to_a_vector_gives_a_point() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = Tuple.createPoint(3, -2, 5);
        Tuple scdTuple = Tuple.createVector(-2, 3, 1);
        Tuple supposedResult = Tuple.createPoint(1, 1, 6);
        Assertions.assertEquals(supposedResult, fstTuple.add(scdTuple));
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("Subtracting a vector from a point should give a point")
    void tuple_subtract_vector_from_a_point_gives_a_point() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = Tuple.createPoint(3, 2, 1);
        Tuple scdTuple = Tuple.createVector(5, 6, 7);
        Tuple supposedResult = Tuple.createPoint(-2, -4, -6);
        Assertions.assertEquals(supposedResult, fstTuple.subtract(scdTuple));
        Assertions.assertTrue(supposedResult.isPoint());
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("Subtracting two vectors should give a vector")
    void tuple_subtract_two_vectors_gives_a_vector() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = Tuple.createVector(3, 2, 1);
        Tuple scdTuple = Tuple.createVector(5, 6, 7);
        Tuple realResult = fstTuple.subtract(scdTuple);
        Tuple supposedResult = Tuple.createVector(-2, -4, -6);
        Assertions.assertEquals(supposedResult, realResult);
        Assertions.assertTrue(realResult.isVector());
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("Multiplying a tuple by a scalar should upscale a tuple")
    void tuple_multiply_tuple_by_a_scalar_upscale_a_tuple() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = new Tuple(1, -2, 3, -4);
        Tuple supposedResult = new Tuple(3.5, -7, 10.5, -14);
        Assertions.assertEquals(fstTuple.multiply(3.5), supposedResult);
    }

    @Test
    @Tag("OperationTest")
    @DisplayName("Multiplying a tuple by a fraction should descale a tuple")
    void tuple_multiply_tuple_by_a_fraction_descale_a_tuple() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = new Tuple(1, -2, 3, -4);
        Tuple supposedResult = new Tuple(0.5, -1, 1.5, -2);
        Assertions.assertEquals(fstTuple.multiply(0.5), supposedResult);
    }

    @Test
    @Tag("MathTest")
    @DisplayName("Magnitude of the vector (1, 0, 0) should be 1")
    void tuple_magnitude_of_the_vector_100_is_1() {
        Tuple tuple = Tuple.createVector(1, 0, 0);
        Assertions.assertEquals(1.0, tuple.magnitude());
    }

    @Test
    @Tag("MathTest")
    @DisplayName("Magnitude of the vector (0, 1, 0) should be 1")
    void tuple_magnitude_of_the_vector_010_is_1() {
        Tuple tuple = Tuple.createVector(0, 1, 0);
        Assertions.assertEquals(1.0, tuple.magnitude());
    }

    @Test
    @Tag("MathTest")
    @DisplayName("Magnitude of the vector (0, 0, 1) should be 1")
    void tuple_magnitude_of_the_vector_001_is_1() {
        Tuple tuple = Tuple.createVector(0, 0, 1);
        Assertions.assertEquals(1.0, tuple.magnitude());
    }

    @Test
    @Tag("MathTest")
    @DisplayName("Magnitude of the vector (1, 2, 3) should be sqrt(14)")
    void tuple_magnitude_of_the_vector_123_is_sqrt_14() {
        Tuple tuple = Tuple.createVector(1, 2, 3);
        Assertions.assertEquals(Math.sqrt(14), tuple.magnitude());
    }

    @Test
    @Tag("MathTest")
    @DisplayName("Magnitude of the vector (-1, -2, -3) should be sqrt(14)")
    void tuple_magnitude_of_the_vector_negative_123_is_sqrt_14() {
        Tuple tuple = Tuple.createVector(-1, -2, -3);
        Assertions.assertEquals(Math.sqrt(14), tuple.magnitude());
    }

    @Test
    @Tag("MathTest")
    @DisplayName("The dot product of vector (1, 2, 3) and vector (2, 3, 4) should give 20")
    void tuple_dot_product_of_vector_20() {
        Tuple fstTuple = Tuple.createVector(1, 2, 3);
        Tuple scdTuple = Tuple.createVector(2, 3, 4);
        Assertions.assertEquals(20, fstTuple.dot(scdTuple));
    }

    @Test
    @Tag("MathTest")
    @DisplayName("The cross product of vector (1, 2, 3) and vector (2, 3, 4) should give vector (-1, 2, -1)")
    void tuple_cross_product_of_vector_123_and_vector_234() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = Tuple.createVector(1, 2, 3);
        Tuple scdTuple = Tuple.createVector(2, 3, 4);
        Tuple realResult = fstTuple.cross(scdTuple);
        Tuple supposedResult = Tuple.createVector(-1, 2, -1);
        Assertions.assertEquals(supposedResult, realResult);
    }

    @Test
    @Tag("MathTest")
    @DisplayName("The cross product of vector (2, 3, 4) vector (1, 2, 3) should give vector (1, -2, 1)")
    void tuple_cross_product_of_vector_234_and_vector_123() {
        Assumptions.assumeTrue(checkEqualsMethod());
        Tuple fstTuple = Tuple.createVector(1, 2, 3);
        Tuple scdTuple = Tuple.createVector(2, 3, 4);
        Tuple realResult = scdTuple.cross(fstTuple);
        Tuple supposedResult = Tuple.createVector(1, -2, 1);
        Assertions.assertEquals(supposedResult, realResult);
    }

    private static boolean checkEqualsMethod() {
        Tuple fstTuple = Tuple.createPoint(1.0, 2.0, 3.0);
        Tuple scdTuple = Tuple.createPoint(1.0, 2.0, 3.0);
        boolean equalTest = fstTuple.equals(scdTuple);
        Tuple thirdTuple = Tuple.createVector(1.0, 2.0, 3.0);
        boolean notEqualTest = !fstTuple.equals(thirdTuple);
        return equalTest && notEqualTest;
    }

}