package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ConvertUrlToString {

    public static String [] mathlinks = {
            "https://byjus.com/algebra-formulas","Algebra Formulas",
            "https://byjus.com/associative-property-formula","Associative Property",

            "https://byjus.com/absolute-value-formula","Absolute Value Formula",
            "https://byjus.com/addition-formula","Addition Formula",

            "https://byjus.com/anova-formula","Anova Formula",
            "https://byjus.com/average-rate-of-change-formula","Average Rate of Change Formula",

            "https://byjus.com/asymptote-formula","Asymptote Formula",
            "https://byjus.com/axis-of-symmetry-formula","Axis of Symmetry Formula",

            "https://byjus.com/angle-formula","Angle Formula",
            "https://byjus.com/area-of-a-triangle-formula","Area of a Triangle Formula",

            "https://byjus.com/arithmetic-sequence-formula","Arithmetic Sequence Formula",
            "https://byjus.com/average-deviation-formula","Average Deviation Formula",

            "https://byjus.com/area-formulas","Area Formulas",
            "https://byjus.com/area-of-a-pentagon-formula","Area of a Pentagon Formula",

            "https://byjus.com/area-of-a-trapezoid-formula","Area of a Trapezoid Formula",
            "https://byjus.com/area-of-a-rectangle-formula","Area of a Rectangle Formula",

            "https://byjus.com/antiderivative-formula","Antiderivative Formula",
            "https://byjus.com/area-of-a-sector-of-a-circle-formula","Area of a Sector of a Circle Formula",

            "https://byjus.com/arc-length-formula","Arc Length Formula",
            "https://byjus.com/area-formula-for-quadrilaterals","Area Formula for Quadrilaterals",

            "https://byjus.com/area-of-a-sector-formula","Area of a Sector Formula",
            "https://byjus.com/area-of-a-rhombus-formula","Area of a Rhombus Formula",

            "https://byjus.com/area-of-a-parallelogram-formula","Area of a Parallelogram Formula",
            "https://byjus.com/area-of-a-square-formula","Area of a Square Formula",

            "https://byjus.com/area-of-a-circle-formula","Area of a Circle Formula",
            "https://byjus.com/area-of-regular-polygon-formula","Area of Regular Polygon Formula",

            "https://byjus.com/arithmetic-sequence-explicit-formula","Arithmetic Sequence Explicit Formula",
            "https://byjus.com/area-of-a-hexagon-formula","Area of a Hexagon Formula",

            "https://byjus.com/area-of-an-octagon-formula","Area of an Octagon Formula",
            "https://byjus.com/area-under-the-curve-formula","Area under the Curve Formula",

            "https://byjus.com/area-of-isosceles-triangle-formula","Area of Isosceles Triangle Formula",
            "https://byjus.com/binary-formula","Binary Formula",

            "https://byjus.com/area-of-a-segment-of-a-circle-formula","Area of a Segment of a Circle Formula",
            "https://byjus.com/arithmetic-sequence-recursive-formula","Arithmetic Sequence Recursive Formula",

            "https://byjus.com/annulus-formula","Annulus Formula",
            "https://byjus.com/arithmetic-mean-formula","Arithmetic Mean Formula",

            "https://byjus.com/binomial-distribution-formula","Binomial Distribution Formula",
            "https://byjus.com/arctan-formula","Arctan Formula",

            "https://byjus.com/binomial-probability-formula","Binomial Probability Formula",
            "https://byjus.com/binary-to-decimal-formula","Binary to Decimal Formula",

            "https://byjus.com/basic-math-formulas","Basic Math Formulas",
            "https://byjus.com/cofunction-formulas","Cofunction Formulas",

            "https://byjus.com/area-of-equilateral-triangle-formula","Area of Equilateral Triangle Formula",
            "https://byjus.com/calculus-formulas","Calculus Formulas",

            "https://byjus.com/commutative-property-formula","Commutative Property",
            "https://byjus.com/binomial-expansion-formula","Binomial Expansion Formula",

            "https://byjus.com/chi-square-formula","Chi Square Formula",
            "https://byjus.com/bayes-theorem-formula","Bayes Theorem Formula",

            "https://byjus.com/conditional-probability-formula","Conditional Probability Formula",
            "https://byjus.com/correlation-coefficient-formula","Correlation Coefficient Formula",

            "https://byjus.com/complex-number-formula","Complex Number Formula",
            "https://byjus.com/covariance-formula","Covariance Formula",

            "https://byjus.com/combination-formula","Combination Formula",
            "https://byjus.com/confidence-interval-formula","Confidence Interval Formula",

            "https://byjus.com/cpk-formula","Cpk Formula",
            "https://byjus.com/coefficient-of-determination-formula","Coefficient of Determination Formula",

            "https://byjus.com/chain-rule-formula","Chain Rule Formula",
            "https://byjus.com/coin-toss-probability-formula","Coin Toss Probability Formula",

            "https://byjus.com/celsius-formula","Celsius Formula",
            "https://byjus.com/compound-interest-formula","Compound Interest Formula",

            "https://byjus.com/cp-formula","Cp Formula",
            "https://byjus.com/cube-root-formula","Cube Root Formula",

            "https://byjus.com/central-limit-theorem-formula","Central Limit Theorem Formula",
            "https://byjus.com/completing-the-square-formula","Completing the Square Formula",

            "https://byjus.com/covariance-matrix-formula","Covariance Matrix Formula",
            "https://byjus.com/change-of-base-formula","Change of Base Formula",

            "https://byjus.com/complex-number-division-formula","Complex Number Division Formula",
            "https://byjus.com/cross-product-formula","Cross Product Formula",

            "https://byjus.com/cubic-equation-formula","Cubic Equation Formula",
            "https://byjus.com/coefficient-of-variation-formula","Coefficient of Variation Formula",

            "https://byjus.com/cube-formula","Cube Formula",
            "https://byjus.com/central-angle-of-a-circle-formula","Central Angle of a Circle Formula",

            "https://byjus.com/cofactor-formula","Cofactor Formula",
            "https://byjus.com/complex-number-power-formula","Complex Number Power Formula",

            "https://byjus.com/circumference-formula","Circumference Formula",
            "https://byjus.com/cosine-formula","Cosine Formula",

            "https://byjus.com/difference-quotient-formula","Difference Quotient Formula",
            "https://byjus.com/consecutive-integers-formula","Consecutive Integers Formula",

            "https://byjus.com/circle-graph-formula","Circle Graph Formula",
            "https://byjus.com/centroid-of-a-trapezoid-formula","Centroid of a Trapezoid Formula",

            "https://byjus.com/chord-length-formula","Chord Length Formula",
            "https://byjus.com/direct-variation-formula","Direct Variation Formula",

            "https://byjus.com/circumcenter-formula","Circumcenter Formula",
            "https://byjus.com/division-formula","Division Formula",

            "https://byjus.com/derivative-formula","Derivative Formula",
            "https://byjus.com/decay-formula","Decay Formula",

            "https://byjus.com/dot-product-formula","Dot Product Formula",
            "https://byjus.com/vector-formulas","Vector Formulas",

            "https://byjus.com/de-moivre-formula","De Moivre Formula",
            "https://byjus.com/volume-of-a-sphere-formula","Volume of a Sphere Formula",

            "https://byjus.com/decimal-to-fraction-formula","Decimal to Fraction Formula",
            "https://byjus.com/daily-compound-interest-formula","Daily Compound Interest Formula",

            "https://byjus.com/diagonal-formula","Diagonal Formula",
            "https://byjus.com/determinant-formula","Determinant Formula",

            "https://byjus.com/direction-of-a-vector-formula","Direction of a Vector Formula",
            "https://byjus.com/diagonal-of-a-cube-formula","Diagonal of a Cube Formula",

            "https://byjus.com/definite-integral-formula","Definite Integral Formula",
            "https://byjus.com/difference-of-cubes-formula","Difference of Cubes Formula",

            "https://byjus.com/ellipse-formula","Ellipse Formula",
            "https://byjus.com/equation-formula","Equation Formula",

            "https://byjus.com/discriminant-formula","Discriminant Formula",
            "https://byjus.com/exponential-function-formula","Exponential Function Formula",

            "https://byjus.com/empirical-probability-formula","Empirical Probability Formula",
            "https://byjus.com/double-angle-formulas","Double Angle Formulas",

            "https://byjus.com/effect-size-formula","Effect Size Formula",
            "https://byjus.com/equation-of-a-circle-formula","Equation of a Circle Formula",

            "https://byjus.com/exponents-formula","Exponents Formula",
            "/gross-profit-formula","Gross Profit Formula",

            "https://byjus.com/exponential-distribution-formula","Exponential Distribution Formula",
            "https://byjus.com/foil-formula","Foil Formula",

            "https://byjus.com/fibonacci-formula","Fibonacci Formula",
            "https://byjus.com/equilateral-triangle-formula","Equilateral Triangle Formula",

            "https://byjus.com/f-test-formula","F Test Formula",
            "https://byjus.com/geometry-formulas","Geometry Formulas",

            "https://byjus.com/frustum-of-a-right-circular-cone-formula","Frustum of a Right Circular Cone Formula",
            "https://byjus.com/function-notation-formula","Function Notation Formula",

            "https://byjus.com/geometric-sequence-formula","Geometric Sequence Formula",
            "https://byjus.com/geometric-distribution-formula","Geometric Distribution Formula",

            "https://byjus.com/fourier-series-formula","Fourier Series Formula",
            "https://byjus.com/hyperbolic-function-formula","Hyperbolic Function Formula",

            "https://byjus.com/factorial-formula","Factorial Formula",
            "https://byjus.com/hyperbola-formula","Hyperbola Formula",

            "https://byjus.com/half-angle-formula","Half Angle formula",
            "https://byjus.com/graphs-of-trigonometric-functions-formula","Graphs of Trigonometric Functions Formula",

            "https://byjus.com/half-life-formula","Half Life Formula",
            "https://byjus.com/inverse-variation-formula","Inverse Variation Formula",

            "https://byjus.com/geometric-series-formula","Geometric Series Formula",
            "https://byjus.com/interest-formula","Interest Formula",

            "https://byjus.com/infinite-series-formula","Infinite Series Formula",
            "https://byjus.com/hypothesis-testing-formula","Hypothesis Testing Formula",

            "https://byjus.com/implicit-differentiation-formula","Implicit Differentiation Formula",
            "https://byjus.com/height-of-a-parallelogram-formula","Height of a Parallelogram Formula",

            "https://byjus.com/inverse-function-formula","Inverse Function Formula",
            "https://byjus.com/integration-by-substitution-formula","Integration by Substitution Formula",

            "https://byjus.com/inverse-hyperbolic-functions-formula","Inverse Hyperbolic Functions Formula",
            "https://byjus.com/isosceles-trapezoid-formula","Isosceles Trapezoid Formula",

            "https://byjus.com/law-of-sines-formula","Law of Sines Formula",
            "https://byjus.com/isosceles-triangle-perimeter-formula","Isosceles Triangle Perimeter Formula",

            "https://byjus.com/linear-regression-formula","Linear Regression Formula",
            "https://byjus.com/loan-balance-formula","Loan Balance Formula",

            "https://byjus.com/lagrange-interpolation-formula","Lagrange Interpolation Formula",
            "https://byjus.com/interquartile-range-formula","Interquartile Range Formula",

            "https://byjus.com/linear-approximation-formula","Linear Approximation Formula",
            "https://byjus.com/x-and-y-intercept-formula","X and Y Intercept Formula",

            "https://byjus.com/limit-formula","Limit Formula",
            "https://byjus.com/mean-deviation-formula","Mean Deviation Formula",

            "https://byjus.com/maclaurin-series-formula","Maclaurin Series Formula",
            "https://byjus.com/magnitude-of-a-vector-formula","Magnitude of a Vector Formula",

            "https://byjus.com/monthly-compound-interest-formula","Monthly Compound Interest Formula",
            "https://byjus.com/normal-distribution-formula","Normal Distribution Formula",

            "https://byjus.com/orthocenter-formula","Orthocenter Formula",
            "https://byjus.com/newtons-method-formula","Newton&#8217;s Method Formula",

            "https://byjus.com/percentage-formula","Percentage Formula",
            "https://byjus.com/pyramid-formula","Pyramid Formula",

            "https://byjus.com/profit-margin-formula","Profit Margin Formula",
            "https://byjus.com/parallelogram-formula","Parallelogram Formula",

            "https://byjus.com/product-to-sum-formula","Product to Sum Formula",
            "https://byjus.com/population-mean-formula","Population Mean Formula",

            "https://byjus.com/partial-differential-equations-formula","Partial Differential Equations",
            "https://byjus.com/product-rule-formula","Product Rule Formula",

            "https://byjus.com/profit-formula","Profit Formula",
            "https://byjus.com/perfect-square-trinomial-formula","Perfect Square Trinomial Formula",

            "https://byjus.com/percentage-increase-formula","Percentage Increase Formula",
            "https://byjus.com/perimeter-of-a-square-formula","Perimeter of a Square Formula",

            "https://byjus.com/t-distribution-formula","T-Distribution Formula",
            "https://byjus.com/point-of-intersection-formula","Point of Intersection Formula",

            "https://byjus.com/perimeter-of-a-triangle-formula","Perimeter of a Triangle Formula",
            "https://byjus.com/perpendicular-line-formula","Perpendicular Formula",

            "https://byjus.com/pythagorean-triples-formula","Pythagorean Triples Formula",
            "https://byjus.com/percentage-yield-formula","Percentage Yield Formula",

            "https://byjus.com/volume-of-a-rectangular-prism-formula","Volume of a Rectangular Prism Formula",
            "https://byjus.com/revenue-formula","Revenue Formula",

            "https://byjus.com/rectangle-formula","Rectangle Formula",
            "https://byjus.com/relative-frequency-formula","Relative Frequency Formula",

            "https://byjus.com/pearson-correlation-formula","Pearson Correlation Formula",
            "https://byjus.com/ratio-analysis-formula","Ratio Analysis Formula",

            "https://byjus.com/platonic-solids-formula","Platonic Solids Formula",
            "https://byjus.com/regression-sum-of-squares-formula","Regression Sum of Squares Formula",

            "https://byjus.com/quotient-rule-formula","Quotient Rule Formula",
            "https://byjus.com/statistics-formulas","Statistics Formulas",

            "https://byjus.com/resultant-vector-formula","Resultant Vector Formula",
            "https://byjus.com/standard-form-formula","Standard Form Formula",

            "https://byjus.com/right-triangle-formula","Right Triangle Formula",
            "https://byjus.com/slope-formula","Slope Formula",

            "https://byjus.com/root-mean-square-formula","Root Mean Square Formula",
            "https://byjus.com/summation-formula","Summation Formula",

            "https://byjus.com/radius-formula","Radius Formula",
            "https://byjus.com/resistors-in-parallel-formula","Resistors in Parallel Formula",

            "https://byjus.com/subtraction-formulas","Subtraction Formulas",
            "https://byjus.com/series-formula","Series Formula",

            "https://byjus.com/signal-to-noise-ratio-formula","Signal to Noise Ratio Formula",
            "https://byjus.com/sphere-formula","Sphere Formula",

            "https://byjus.com/surface-area-of-a-cone-formula","Surface Area of a Cone Formula",
            "https://byjus.com/sample-size-formula","Sample Size Formula",

            "https://byjus.com/square-root-property-formula","Square Root Property Formula",
            "https://byjus.com/surface-area-of-a-prism-formula","Surface Area of a Prism Formula",

            "https://byjus.com/sample-mean-formula","Sample Mean Formula",
            "https://byjus.com/simple-interest-formula","Simple Interest Formula",

            "https://byjus.com/sum-of-squares-formula","Sum of Squares Formula",
            "https://byjus.com/spherical-cap-volume-formulas","Spherical Cap Volume Formulas",

            "https://byjus.com/spherical-sector-formula","Spherical Sector Formula",
            "https://byjus.com/slant-asymptote-formula","Slant Asymptote Formula",

            "https://byjus.com/tangent-line-formula","Tangent Formula",
            "https://byjus.com/surface-area-of-a-rectangular-prism-formula","Surface Area of a Rectangular Prism Formula",

            "https://byjus.com/taylor-series-formula","Taylor Series Formula",
            "https://byjus.com/triangular-pyramid-formula","Triangular Pyramid Formula",

            "https://byjus.com/trigonometry-formulas","Trigonometry Formulas",
            "https://byjus.com/the-distance-formula","The Distance Formula",

            "https://byjus.com/unit-circle-formula","Unit Circle Formula",
            "https://byjus.com/t-test-formula","T Test Formula",

            "https://byjus.com/vector-projection-formula","Vector Projection Formula",
            "https://byjus.com/unit-rate-formula","Unit Rate Formula",

            "https://byjus.com/volume-of-a-cone-formula","Volume of a Cone Formula",
            "https://byjus.com/volume-formulas","Volume Formulas",

            "https://byjus.com/volume-of-a-triangular-prism-formula","Volume of a Triangular Prism Formula",
            "https://byjus.com/volume-of-a-cube-formula","Volume of a Cube Formula",

            "https://byjus.com/x-intercept-formula","X Intercept Formula",
            "https://byjus.com/volume-of-an-ellipsoid-formula","Volume of an Ellipsoid Formula",

            "https://byjus.com/vietas-formula","Vieta&#8217;s Formula",
            "https://byjus.com/y-intercept-formula","Y Intercept Formula",

            "https://byjus.com/distributive-property-formula","Distributive Property",
            "https://byjus.com/difference-of-squares-formula","Difference of Squares Formula",

            "https://byjus.com/linear-interpolation-formula","Linear Interpolation Formula",
            "https://byjus.com/discount-formula","Discount Formula",

            "https://byjus.com/linear-correlation-coefficient-formula","Linear Correlation Coefficient Formula",
            "https://byjus.com/mean-median-mode-formula","Mean Median Mode Formula",

            "https://byjus.com/diameter-formula","Diameter Formula",
            "https://byjus.com/multiple-angle-formulas","Multiple Angle Formulas",

            "https://byjus.com/natural-log-formula","Natural Log Formula",
            "https://byjus.com/probability-formulas","Probability Formulas",

            "https://byjus.com/parabola-formula","Parabola Formula",
            "https://byjus.com/pythagorean-theorem-formula","Pythagorean Theorem Formula",

            "https://byjus.com/probability-distribution-formula","Probability Distribution Formula",
            "https://byjus.com/trapezoidal-rule-formula","Trapezoidal Rule Formula",

            "https://byjus.com/euler-maclaurin-formula","Euler Maclaurin Formula",
            "https://byjus.com/exponential-equation-formula","Exponential Equation Formula",

            "https://byjus.com/factoring-formulas","Factoring Formulas",
            "https://byjus.com/function-formulas","Function Formulas",

            "https://byjus.com/percentage-change-formula","Percentage Change Formula",
            "https://byjus.com/percent-error-formula","Percent Error Formula",

            "https://byjus.com/perimeter-of-a-parallelogram-formula","Perimeter of a Parallelogram Formula",
            "https://byjus.com/perimeter-of-a-kite-formula","Perimeter of a Kite Formula",

            "https://byjus.com/volume-charge-density-formula","Volume Charge Density Formula",
            "https://byjus.com/triangle-formula","Triangle Formula",

            "https://byjus.com/perimeter-of-hexagon-formula","Perimeter of Hexagon Formula",
            "https://byjus.com/quadratic-function-formula","Quadratic Function Formula",

            "https://byjus.com/degree-and-radian-measure-formula","Degree and Radian Measure Formula",
            "https://byjus.com/degrees-of-freedom-formula","Degrees of Freedom Formula",

            "https://byjus.com/decimal-to-binary-formula","Decimal to Binary Formula",
            "https://byjus.com/double-time-formula","Double Time Formula",

            "https://byjus.com/rate-of-change-formula","Rate of Change Formula",
            "https://byjus.com/reduction-formula","Reduction Formula",

            "https://byjus.com/radians-to-degrees-formula","Radians to Degrees Formula",
            "https://byjus.com/diagonal-of-parallelogram-formula","Diagonal of Parallelogram Formula",

            "https://byjus.com/equation-of-a-line-formula","Equation of a Formula",
            "https://byjus.com/exponential-growth-formula","Exponential Growth Formula",

            "https://byjus.com/eulers-formula","Euler&#8217;s Formula",
            "https://byjus.com/factoring-trinomials-formula","Factoring Trinomials Formula",

            "https://byjus.com/fahrenheit-to-celsius-formula","Fahrenheit to Celsius Formula",
            "https://byjus.com/great-circle-formula","Great Circle Formula",

            "https://byjus.com/geometric-mean-formula","Geometric Mean Formula",
            "https://byjus.com/hypergeometric-distribution-formula","Hypergeometric Distribution Formula",

            "https://byjus.com/rectangular-parallelepiped-formula","Rectangular Parallelepiped Formula",
            "https://byjus.com/radius-of-curvature-formula","Radius of Curvature Formula",

            "https://byjus.com/surface-area-formulas","Surface Area Formulas",
            "https://byjus.com/square-root-formula","Square Root Formula",

            "https://byjus.com/skewness-formula","Skewness Formula",
            "https://byjus.com/sampling-error-formula","Sampling Error Formula",

            "https://byjus.com/surface-area-of-a-pyramid-formula","Surface Area of a Pyramid Formula",
            "https://byjus.com/simpsons-rule-formula","Simpson&#8217;s Rule Formula",

            "https://byjus.com/surface-area-of-a-cylinder-formula","Surface Area of a Cylinder Formula",
            "https://byjus.com/harmonic-mean-formula","Harmonic Mean Formula",

            "https://byjus.com/integral-formula","Integral Formula",
            "https://byjus.com/isosceles-triangle-formula","Isosceles Triangle Formula",

            "https://byjus.com/infinite-geometric-series-formula","Infinite Geometric Series Formula",
            "https://byjus.com/stirling-formula","Stirling Formula",

            "https://byjus.com/spherical-segment-formula","Spherical Segment Formula",
            "https://byjus.com/temperature-conversion-formula","Temperature Conversion Formula",

            "https://byjus.com/tangent-addition-formula","Tangent Addition Formula",
            "https://byjus.com/tangent-circle-formula","Tangent Circle Formula",

            "https://byjus.com/u-substitution-formula","U Substitution Formula",
            "https://byjus.com/variance-formula","Variance Formula",

            "https://byjus.com/volume-of-a-cylinder-formula","Volume of a Cylinder Formula",
            "https://byjus.com/volume-of-a-square-pyramid-formula","Volume of a Square Pyramid Formula",

            "https://byjus.com/weighted-average-formula","Weighted Average Formula",
            "https://byjus.com/z-score-formula","Z Score Formula",

            "https://byjus.com/frequency-distribution-formula","Frequency Distribution Formula",
            "https://byjus.com/frustum-of-a-regular-pyramid-formula","Frustum of a Regular Pyramid Formula",

            "https://byjus.com/gaussian-distribution-formula","Gaussian Distribution Formula",
            "https://byjus.com/hexagon-formula","Hexagon Formula",

            "https://byjus.com/hexagonal-pyramid-formula","Hexagonal Pyramid Formula",
            "https://byjus.com/interpolation-formula","Interpolation Formula",

            "https://byjus.com/inverse-matrix-formula","Inverse Matrix Formula",
            "https://byjus.com/integration-by-parts-formula","Integration by Parts Formula",

            "https://byjus.com/law-of-cosines-formula","Law of Cosines Formula",
            "https://byjus.com/lcm-formula","LCM Formula",

            "https://byjus.com/linear-function-formula","Linear Function Formula",
            "https://byjus.com/midpoint-formula","Midpoint Formula",

            "https://byjus.com/mean-value-theorem-formula","Mean Value Theorem Formula",
            "https://byjus.com/n-choose-k-formula","N Choose K Formula",

            "https://byjus.com/permutations-and-combinations-formulas","Permutations and Combinations Formulas",
            "https://byjus.com/perimeter-formulas","Perimeter Formulas",

            "https://byjus.com/polygon-formula","Polygon Formula",
            "https://byjus.com/logarithm-formula","Logarithm Formula",

            "https://byjus.com/line-of-best-fit-formula","Line of Best Fit Formula",
            "https://byjus.com/linear-equations-formula","Linear Equations Formula",

            "https://byjus.com/matrix-formula","Matrix Formula",
            "https://byjus.com/margin-of-error-formula","Margin of Error Formula",

            "https://byjus.com/mean-absolute-deviation-formula","Mean Absolute Deviation Formula",
            "https://byjus.com/octagon-formula","Octagon Formula",

            "https://byjus.com/polynomial-formula","Polynomial Formula",
            "https://byjus.com/prism-formula","Prism Formula",

            "https://byjus.com/periodic-formulas","Periodic Formulas",
            "https://byjus.com/permutation-formula","Permutation Formula",

            "https://byjus.com/perfect-square-formula","Perfect Square Formula",
            "https://byjus.com/percentile-formula","Percentile Formula",

            "https://byjus.com/point-slope-form-formula","Point Slope Form Formula",
            "https://byjus.com/perimeter-of-a-trapezoid-formula","Perimeter of a Trapezoid Formula",

            "https://byjus.com/point-gradient-formula","Point Gradient Formula",
            "https://byjus.com/spherical-wedge-and-spherical-lune-formula","Spherical Wedge and Spherical Lune Formula",

            "https://byjus.com/perimeter-of-rhombus-formula","Perimeter of Rhombus Formula",
            "https://byjus.com/quadratic-interpolation-formula","Quadratic Interpolation Formula",

            "https://byjus.com/ratio-formula","Ratio Formula",
            "https://byjus.com/riemann-sum-formula","Riemann Sum Formula",

            "https://byjus.com/relative-standard-deviation-formula","Relative Standard Deviation Formula",
            "https://byjus.com/regular-square-pyramid-formula","Regular Square Pyramid Formula",

            "https://byjus.com/regular-tetrahedron-formula","Regular Tetrahedron Formula",
            "https://byjus.com/prime-number-formula","Prime Number Formula",

            "https://byjus.com/percentage-decrease-formula","Percentage Decrease Formula",
            "https://byjus.com/proportion-formula","Proportion Formula",

            "https://byjus.com/perimeter-of-a-rectangle-formula","Perimeter of a Rectangle Formula",
            "https://byjus.com/parallel-line-formula","Parallel Formula",

            "https://byjus.com/poisson-distribution-formula","Poisson Distribution Formula",
            "https://byjus.com/percent-composition-formula","Percent Composition Formula",

            "https://byjus.com/quartile-formula","Quartile Formula",
            "https://byjus.com/square-footage-formula","Square Footage Formula",

            "https://byjus.com/sum-of-cubes-formula","Sum of Cubes Formula",
            "https://byjus.com/sine-cosine-tangent-formula","Sine Cosine Tangent Formula",

            "https://byjus.com/standard-error-formula","Standard Error Formula",
            "https://byjus.com/surface-area-of-a-cube-formula","Surface Area of a Cube Formula",

            "https://byjus.com/radical-formula","Radical Formula",
            "https://byjus.com/slope-intercept-form-formula","Slope Intercept Form Formula",

            "https://byjus.com/rotation-formula","Rotation Formula",
            "https://byjus.com/surface-area-of-circle-formula","Surface Area of Circle Formula",

            "https://byjus.com/r-squared-formula","R Squared Formula",
            "https://byjus.com/sine-formula","Sine Formula",

            "https://byjus.com/right-angle-formula","Right Angle Formula",
            "https://byjus.com/sum-of-arithmetic-sequence-formula","Sum of Arithmetic Sequence Formula",

            "https://byjus.com/retention-factor-formula","Retention Factor Formula",
            "https://byjus.com/trajectory-formula","Trajectory Formula",

            "https://byjus.com/scientific-notation-formula","Scientific Notation Formula",
            "https://byjus.com/tangential-quadrilateral-formula","Tangential Quadrilateral Formula",

            "https://byjus.com/sequence-formula","Sequence Formula",
            "https://byjus.com/tangent-formula","Tangent Formula",

            "https://byjus.com/square-formula","Square Formula",
            "https://byjus.com/unit-vector-formula","Unit Vector Formula",

            "https://byjus.com/standard-deviation-formula","Standard Deviation Formula",
            "https://byjus.com/vertex-formula","Vertex Formula",

            "https://byjus.com/statistical-significance-formula","Statistical Significance Formula",
            "https://byjus.com/volume-of-parallelepiped-formula","Volume of Parallelepiped Formula",

            "https://byjus.com/surface-area-of-a-triangular-prism-formula","Surface Area of a Triangular Prism Formula",
            "https://byjus.com/volume-of-a-pyramid-formula","Volume of a Pyramid Formula",

            "https://byjus.com/side-angle-side-formula","Side Angle Side Formula",
            "https://byjus.com/weighted-mean-formula","Weighted Mean Formula",

            "https://byjus.com/surface-area-of-a-sphere-formula","Surface Area of a Sphere Formula",
            "https://byjus.com/surface-area-of-a-square-pyramid-formula","Surface Area of a Square Pyramid Formula"};



    public static String[] physicsURLS = {
            "https://byjus.com/absolute-pressure-formula","Absolute Pressure Formula",
            "https://byjus.com/acceleration-formula","Acceleration Formula",

            "https://byjus.com/angle-between-two-vectors-formula","Angle between Two Vectors Formula",
            "https://byjus.com/average-force-formula","Average Force Formula",

            "https://byjus.com/amplitude-formula","Amplitude Formula",
            "https://byjus.com/acceleration-due-to-gravity-formula","Acceleration due to Gravity Formula",

            "https://byjus.com/archimedes-principle-formula","Archimedes Principle Formula",
            "https://byjus.com/amperes-law-formula","Ampere&#8217;s Law Formula",

            "https://byjus.com/angular-acceleration-formula","Angular Acceleration Formula",
            "https://byjus.com/air-resistance-formula","Air Resistance Formula",

            "https://byjus.com/angular-momentum-formula","Angular Momentum Formula",
            "https://byjus.com/angular-displacement-formula","Angular Displacement Formula",

            "https://byjus.com/average-speed-formula","Average Speed Formula",
            "https://byjus.com/angular-speed-formula","Angular Speed Formula",

            "https://byjus.com/average-acceleration-formula","Average Acceleration Formula",
            "https://byjus.com/angular-velocity-formula","Angular Velocity Formula",

            "https://byjus.com/buoyancy-formula","Buoyancy Formula",
            "https://byjus.com/beat-frequency-formula","Beat Frequency Formula",

            "https://byjus.com/brewsters-law-formula","Brewster&#8217;s Law",
            "https://byjus.com/average-velocity-formula","Average Velocity Formula",

            "https://byjus.com/bulk-modulus-formula","Bulk Modulus Formula",
            "https://byjus.com/beam-deflection-formula","Beam Deflection Formula",

            "https://byjus.com/capacitance-formula","Capacitance Formula",
            "https://byjus.com/buffer-solution-formula","Buffer Solution Formula",

            "https://byjus.com/bernoullis-equation-formula","Bernoulli’s Equation Formula",
            "https://byjus.com/center-of-mass-formula","Center of Mass Formula",

            "https://byjus.com/current-density-formula","Current Density Formula",
            "https://byjus.com/cone-formula","Cone Formula",

            "https://byjus.com/cylinder-formula","Cylinder Formula",
            "https://byjus.com/circle-formula","Circle Formula",

            "https://byjus.com/cyclic-quadrilateral-formula","Cyclic Quadrilateral Formula",
            "https://byjus.com/capacitive-reactance-formula","Capacitive Reactance Formula",

            "https://byjus.com/centripetal-force-formula","Centripetal Force Formula",
            "https://byjus.com/centroid-formula","Centroid Formula",

            "https://byjus.com/circular-velocity-formula","Circular Velocity Formula",
            "https://byjus.com/coulombs-law-formula","Coulomb&#8217;s Law Formula",

            "https://byjus.com/critical-angle--formula","Critical Angle Formula",
            "https://byjus.com/cylindrical-capacitor-formula","Cylindrical Capacitor Formula",

            "https://byjus.com/charge-density-formula","Charge Density Formula",
            "https://byjus.com/combustion-formula","Combustion Formula",

            "https://byjus.com/centripetal-acceleration-formula","Centripetal Acceleration Formula",
            "https://byjus.com/cell-potential-formula","Cell Potential Formula",

            "https://byjus.com/calorimetry-formula","Calorimetry Formula",
            "https://byjus.com/conservation-of-energy-formula","Conservation of Energy Formula",

            "https://byjus.com/coefficient-of-static-friction-formula","Coefficient of Static Friction Formula",
            "https://byjus.com/distance-speed-time-formula","Distance Speed Time Formula",

            "https://byjus.com/doppler-shift-formula","Doppler Shift Formula",
            "https://byjus.com/deceleration-formula","Deceleration Formula",

            "https://byjus.com/electric-field-formula","Electric Field Formula",
            "https://byjus.com/dc-voltage-drop-formula","DC Voltage Drop Formula",

            "https://byjus.com/efficiency-formula","Efficiency Formula",
            "https://byjus.com/elastic-potential-energy-formula","Elastic Potential Energy Formula",

            "https://byjus.com/electric-power-formula","Electric Power Formula",
            "https://byjus.com/energy-density-formula","Energy Density Formula",

            "https://byjus.com/friction-formula","Friction Formula",
            "https://byjus.com/escape-speed-formula","Escape Speed Formula",

            "https://byjus.com/friction-force-formula","Friction Force Formula",
            "https://byjus.com/formula-dynamics-formula","Formula Dynamics",

            "https://byjus.com/gravity-formula","Gravity Formula",
            "https://byjus.com/flow-rate-formula","Flow Rate Formula",

            "https://byjus.com/gauss-law-formula","Gauss Law Formula",
            "https://byjus.com/gregory-newton-formula","Gregory Newton Formula",

            "https://byjus.com/heat-formula","Heat Formula",
            "https://byjus.com/gram-formula-mass","Gram Formula Mass",

            "https://byjus.com/heat-of-vaporization-formula","Heat of Vaporization Formula",
            "https://byjus.com/heat-transfer-formula","Heat Transfer Formula",

            "https://byjus.com/heat-gain-formula","Heat Gain Formula",
            "https://byjus.com/heat-load-formula","Heat Load Formula",

            "https://byjus.com/continuous-compound-interest-formula","Continuous Compound Interest Formula",
            "https://byjus.com/density-formula","Density Formula",

            "https://byjus.com/de-broglie-wavelength-formula","De Broglie Wavelength Formula",
            "https://byjus.com/doppler-effect-formula","Doppler Effect Formula",

            "https://byjus.com/displacement-formula","Displacement Formula",
            "https://byjus.com/diffraction-grating-formula","Diffraction Grating Formula",

            "https://byjus.com/dynamic-viscosity-formula","Dynamic Viscosity Formula",
            "https://byjus.com/elastic-collision-formula","Elastic Collision Formula",

            "https://byjus.com/electric-current-formula","Electric Current Formula",
            "https://byjus.com/force-formula","Force Formula",

            "https://byjus.com/friction-loss-formula","Friction Loss Formula",
            "https://byjus.com/froude-number-formula","Froude Number Formula",

            "https://byjus.com/gravitational-field--formula","Gravitational Field Formula",
            "https://byjus.com/hookes-law-formula","Hooke&#8217;s Law Formula",

            "https://byjus.com/heat-of-fusion-formula","Heat of Fusion Formula",
            "https://byjus.com/heat-of-solution-formula","Heat of Solution Formula",

            "https://byjus.com/heat-conduction-formula","Heat Conduction Formula",
            "https://byjus.com/heat-of-hydration-formula","Heat of Hydration Formula",

            "https://byjus.com/electric-potential-formula","Electric Potential Formula",
            "https://byjus.com/drag-force-formula","Drag Force Formula",

            "https://byjus.com/heat-input-formula","Heat Input Formula",
            "https://byjus.com/heat-rate-formula","Heat Rate Formula",

            "https://byjus.com/inductance-formula","Inductance Formula",
            "https://byjus.com/electrical-formulas","Electrical Formulas",

            "https://byjus.com/distance-traveled-formula","Distance Traveled Formula",
            "https://byjus.com/inelastic-collision-formula","Inelastic Collision Formula",

            "https://byjus.com/equivalent-resistance-formula","Equivalent Resistance Formula",
            "https://byjus.com/instantaneous-rate-of-change-formula","Instantaneous Rate of Change Formula",

            "https://byjus.com/electricity-formulas","Electricity Formulas",
            "https://byjus.com/inductive-reactance-formula","Inductive Reactance Formula",

            "https://byjus.com/intensity-formula","Intensity Formula",
            "https://byjus.com/force-of-attraction-formula","Force of Attraction Formula",

            "https://byjus.com/fluid-mechanics-formula","Fluid Mechanics Formula",
            "https://byjus.com/escape-velocity-formula","Escape Velocity Formula",

            "https://byjus.com/kinematic-viscosity-formula","Kinematic Viscosity Formula",
            "https://byjus.com/gravitational-potential-energy-formula","Gravitational Potential Energy Formula",

            "https://byjus.com/energy-consumption-formula","Energy Consumption Formula",
            "https://byjus.com/kinetic-energy-formula","Kinetic Energy Formula",

            "https://byjus.com/gravitational-acceleration-formula","Gravitational Acceleration Formula",
            "https://byjus.com/linear-speed-formula","Linear Speed Formula",

            "https://byjus.com/latent-heat-formula","Latent Heat formula",
            "https://byjus.com/frequency-formula","Frequency Formula",

            "https://byjus.com/heat-capacity-formula","Heat Capacity Formula",
            "https://byjus.com/lens-makers-formula","Lens Maker&#8217;s Formula",

            "https://byjus.com/magnetism-formula","Magnetism Formula",
            "https://byjus.com/free-fall-formula","Free Fall Formula",

            "https://byjus.com/heat-loss-formula","Heat Loss Formula",
            "https://byjus.com/momentum-formula","Momentum Formula",

            "https://byjus.com/mechanical-energy-formula","Mechanical Energy Formula",
            "https://byjus.com/gravitational-force-formula","Gravitational Force Formula",

            "https://byjus.com/magnetic-force-formula","Magnetic Force Formula",
            "https://byjus.com/momentum-and-its-conservation-formula","Momentum and Its Conservation",

            "https://byjus.com/heisenberg-uncertainty-principle-formula","Heisenberg Uncertainty Principle Formula",
            "https://byjus.com/mass-flow-rate-formula","Mass Flow Rate Formula",

            "https://byjus.com/newtons-second-law-formula","Newton&#8217;s Second Law Formula",
            "https://byjus.com/induced-voltage-formula","Induced Voltage Formula",

            "https://byjus.com/normal-force-formula","Normal Force Formula",
            "https://byjus.com/orbital-velocity-formula","Orbital Velocity Formula",

            "https://byjus.com/initial-velocity-formula","Initial Velocity Formula",
            "https://byjus.com/optics-formula","Optics Formula",

            "https://byjus.com/power-formula","Power Formula",
            "https://byjus.com/kelvin-to-celsius-formula","Kelvin to Celsius Formula",

            "https://byjus.com/pressure-formula","Pressure Formula",
            "https://byjus.com/pressure-drop-formula","Pressure Drop Formula",

            "https://byjus.com/pendulum-formula","Pendulum",
            "https://byjus.com/linear-momentum-formula","Linear Momentum Formula",

            "https://byjus.com/poiseuilles-law-formula","Poiseuille’s Law formula",
            "https://byjus.com/reynolds-number-formula","Reynolds Number Formula",

            "https://byjus.com/measurement-formulas","Measurement Formulas",
            "https://byjus.com/resultant-force-formula","Resultant Force Formula",

            "https://byjus.com/relative-motion-formula","Relative Motion Formula",
            "https://byjus.com/heat-engine-efficiency-formula","Heat Engine Efficiency Formula",

            "https://byjus.com/gay-lussac-law-formula","Gay Lussac Law Formula",
            "https://byjus.com/mechanical-advantage-formula","Mechanical Advantage Formula",

            "https://byjus.com/resistivity--formula","Resistivity Formula",
            "https://byjus.com/relativistic-doppler-effect-formula","Relativistic Doppler Effect Formula",

            "https://byjus.com/heat-release-rate-formula","Heat Release Rate Formula",
            "https://byjus.com/horsepower-formula","Horsepower Formula",

            "https://byjus.com/refractive-index-formula","Refractive Index Formula",
            "https://byjus.com/reflection-and-ray-model-of-light-formula","Reflection and Ray Model of Light",

            "https://byjus.com/magnetic-field-formula","Magnetic Field Formula",
            "https://byjus.com/inverse-square-law-formula","Inverse Square Law Formula",

            "https://byjus.com/heat-index-formula","Heat Index Formula",
            "https://byjus.com/snells-law-formula","Snell&#8217;s Law Formula",

            "https://byjus.com/spring-force-formula","Spring Force Formula",
            "https://byjus.com/mach-number-formula","Mach Number Formula",

            "https://byjus.com/instantaneous-velocity-formula","Instantaneous Velocity Formula",
            "https://byjus.com/heat-flux-formula","Heat Flux Formula",

            "https://byjus.com/speed-of-sound-formula","Speed of Sound Formula",
            "https://byjus.com/strain-energy-formula","Strain Energy Formula",

            "https://byjus.com/kinetic-friction-formula","Kinetic Friction Formula",
            "https://byjus.com/heat-of-reaction-formula","Heat of Reaction formula",

            "https://byjus.com/ohms-law-formula","Ohms Law Formula",
            "https://byjus.com/sound-intensity-formula","Sound Intensity Formula",

            "https://byjus.com/sound-pressure-level-formula","Sound Pressure Level Formula",
            "https://byjus.com/lattice-energy-formula","Lattice Energy Formula",

            "https://byjus.com/impulse-formula","Impulse Formula",
            "https://byjus.com/torque-formula","Torque Formula",

            "https://byjus.com/potential-energy-formula","Potential Energy Formula",
            "https://byjus.com/time-constant--formula","Time Constant Formula",

            "https://byjus.com/light-waves-and-color-formula","Light Waves and Color",
            "https://byjus.com/instantaneous-speed-formula","Instantaneous Speed Formula",

            "https://byjus.com/thermal-conductivity-formula","Thermal Conductivity Formula",
            "https://byjus.com/transformer-formula","Transformer Formula",

            "https://byjus.com/moment-formula","Moment Formula",
            "https://byjus.com/parallel-plate-capacitor-formula","Parallel Plate Capacitor Formula",

            "https://byjus.com/kinematics-formulas","Kinematics Formulas",
            "https://byjus.com/tangential-acceleration-formula","Tangential Acceleration Formula",

            "https://byjus.com/universal-gravitation--formula","Universal Gravitation Formula",
            "https://byjus.com/polarization-formula","Polarization Formula",

            "https://byjus.com/length-contraction-formula","Length Contraction Formula",
            "https://byjus.com/velocity-formula","Velocity Formula",

            "https://byjus.com/work-formula","Work Formula",
            "https://byjus.com/rotational-inertia-formula","Rotational Inertia Formula",

            "https://byjus.com/work-done-by-gravity-formula","Work Done by Gravity Formula",
            "https://byjus.com/wavelength-to-frequency-formula","Wavelength to Frequency Formula",

            "https://byjus.com/wave-speed-formula","Wave Speed Formula",
            "https://byjus.com/wave-energy-formula","Wave Energy Formula",

            "https://byjus.com/resistance-formula","Resistance Formula",
            "https://byjus.com/relative-velocity-formula","Relative Velocity Formula",

            "https://byjus.com/strain-formula","Strain Formula",
            "https://byjus.com/specific-gravity-formula","Specific Gravity Formula",

            "https://byjus.com/specific-heat-formula","Specific Heat Formula",
            "https://byjus.com/stopping-distance-formula","Stopping Distance Formula",

            "https://byjus.com/thermal-expansion-formula","Thermal Expansion Formula",
            "https://byjus.com/tangential-velocity-formula","Tangential Velocity Formula",

            "https://byjus.com/voltage-divider-formula","Voltage Divider Formula",
            "https://byjus.com/weight-formula/","Weight Formula",

            "https://byjus.com/water-pressure-formula","Water Pressure Formula",
            "https://byjus.com/magnetic-flux--formula","Magnetic Flux Formula",

            "https://byjus.com/magnetic-field-in-a-solenoid-formula","Magnetic Field in a Solenoid Formula",
            "https://byjus.com/newtons-law-of-cooling-formula","Newton&#8217;s Law of Cooling Formula",

            "https://byjus.com/physics-formulas","Physics Formulas",
            "https://byjus.com/potential-energy-of-a-spring-formula","Potential Energy of a Spring Formula",

            "https://byjus.com/latent-heat-of-fusion-formula","Latent Heat of Fusion Formula",
            "https://byjus.com/molar-concentration-formula","Molar Concentration Formula",

            "https://byjus.com/mass-formula","Mass Formula",
            "https://byjus.com/moment-of-inertia-formula","Moment of Inertia Formula",

            "https://byjus.com/net-force-formula","Net Force Formula",
            "https://byjus.com/orbital-speed-formula","Orbital Speed Formula",

            "https://byjus.com/projectile-motion-formula","Projectile Motion Formula",
            "https://byjus.com/position-formula","Position Formula",

            "https://byjus.com/pascals-principle-formula","Pascal’s Principle Formula",
            "https://byjus.com/photon-energy--formula","Photon Energy Formula",

            "https://byjus.com/relativity-formula","Relativity Formula",
            "https://byjus.com/relativistic-mass-formula","Relativistic Mass Formula",

            "https://byjus.com/refraction-formula","Refraction Formula",
            "https://byjus.com/stress-formula","Stress Formula",

            "https://byjus.com/static-friction-formula","Static Friction Formula",
            "https://byjus.com/spring-constant-formula","Spring Constant Formula",

            "https://byjus.com/surface-charge-density-formula","Surface Charge Density Formula",
            "https://byjus.com/temperature-formula","Temperature Formula",

            "https://byjus.com/rotational-kinetic-energy-formula","Rotational Kinetic Energy Formula",
            "https://byjus.com/resonant-frequency-formula","Resonant Frequency Formula",

            "https://byjus.com/radiant-energy-formula","Radiant Energy Formula",
            "https://byjus.com/speed-distance-time-formula","Speed Distance Time Formula",

            "https://byjus.com/surface-tension-formula","Surface Tension Formula",
            "https://byjus.com/time-dilation-formula","Time Dilation Formula",

            "https://byjus.com/uniform-circular-motion-formula","Uniform Circular Motion",
            "https://byjus.com/wavelength-formula","Wavelength Formula",

            "https://byjus.com/wave-formula","Wave Formula",
            "https://byjus.com/shear-modulus-formula","Shear Modulus Formula",

            "https://byjus.com/tension-formula","Tension Formula",
            "https://byjus.com/thermal-energy-formula","Thermal Energy Formula",

            "https://byjus.com/terminal-velocity-formula","Terminal Velocity Formula",
            "https://byjus.com/youngs-modulus-formula","Young&#8217;s Modulus Formula",

            "https://byjus.com/voltage-drop-formula","Voltage Drop Formula",
            "https://byjus.com/wind-energy-formula","Wind Energy Formula",

            "https://byjus.com/wave-power-formula","Wave Power Formula"};



    public static String [] chemURLs = {"https://byjus.com/boyles-law-formula","Boyle&#8217;s Law Formula",
            "https://byjus.com/binding-energy-formula","Binding Energy Formula",

            "https://byjus.com/atomic-mass-formula","Atomic Mass Formula",
            "https://byjus.com/activation-energy-formula","Activation Energy Formula",

            "https://byjus.com/chemical-formula","Chemical Formula",
            "https://byjus.com/bond-order-formula","Bond Order Formula",

            "https://byjus.com/daltons-law-formula","Dalton&#8217;s Law Formula",
            "https://byjus.com/chemical-reaction-formula","Chemical Reaction Formula",

            "https://byjus.com/thermodynamics-formulas","Thermodynamics Formulas",
            "https://byjus.com/chemical-compound-formulas","Chemical Compound Formulas",

            "https://byjus.com/enthalpy-formula","Enthalpy Formula",
            "https://byjus.com/dilution-formula","Dilution Formula",

            "https://byjus.com/condensed-structural-formula","Condensed Structural Formula",
            "https://byjus.com/gay-lussacs-law-formula","Gay Lussac&#8217;s Law Formula",

            "https://byjus.com/entropy-formula","Entropy Formula",
            "https://byjus.com/density-of-gas-formula","Density of Gas Formula",

            "https://byjus.com/equilibrium-constant-formula","Equilibrium Constant Formula",
            "https://byjus.com/gibbs-free-energy-formula","Gibbs Free Energy Formula",

            "https://byjus.com/avogadros-law-formula","Avogadro&#8217;s Law Formula",
            "https://byjus.com/boiling-point-formula","Boiling Point Formula",

            "https://byjus.com/charles-law-formula","Charles Law Formula",
            "https://byjus.com/diffusion-formula","Diffusion Formula",

            "https://byjus.com/empirical-formula","Empirical Formula",
            "https://byjus.com/grams-to-moles-formula","Grams to Moles Formula",

            "https://byjus.com/ionic-compound-formula","Ionic Compound Formula",
            "https://byjus.com/molar-mass-formula","Molar Mass Formula",

            "https://byjus.com/mass-percent-formula","Mass Percent Formula",
            "https://byjus.com/net-ionic-formula","Net Ionic Formula",

            "https://byjus.com/percent-by-weight-formula","Percent by Weight Formula",
            "https://byjus.com/rate-of-decay-formula","Rate of Decay Formula",

            "https://byjus.com/specific-heat-capacity-formula","Specific Heat Capacity Formula",
            "https://byjus.com/vapor-pressure-formula","Vapor Pressure Formula",

            "https://byjus.com/ideal-gas-law-formula","Ideal Gas Law Formula",
            "https://byjus.com/limiting-reactant-formula","Limiting Reactant Formula",

            "https://byjus.com/moles-to-grams-formula","Moles to Grams Formula",
            "https://byjus.com/normality-formula","Normality Formula",

            "https://byjus.com/photosynthesis-formula","Photosynthesis Formula",
            "https://byjus.com/rydberg-formula","Rydberg Formula",

            "https://byjus.com/stoichiometry-formula","Stoichiometry Formula",
            "https://byjus.com/gas-pressure-formula","Gas Pressure Formula",

            "https://byjus.com/internal-energy-formula","Internal Energy Formula",
            "https://byjus.com/molarity-formula","Molarity Formula",

            "https://byjus.com/molar-volume-formula","Molar Volume Formula",
            "https://byjus.com/osmotic-pressure-formula","Osmotic Pressure Formula",

            "https://byjus.com/ph-formula","pH Formula",
            "https://byjus.com/structural-formula","Structural Formula",

            "https://byjus.com/sensible-heat-formula","Sensible Heat Formula",
            "https://byjus.com/ionic-strength-formula","Ionic Strength Formula",

            "https://byjus.com/molecular-formula","Molecular Formula",
            "https://byjus.com/molecular-weight-formula","Molecular Weight Formula",

            "https://byjus.com/number-of-moles-formula","Number of Moles Formula",
            "https://byjus.com/percent-by-volume-formula","Percent by Volume Formula",

            "https://byjus.com/radioactive-decay-formula","Radioactive Decay Formula",
            "https://byjus.com/solubility-formula","Solubility Formula",

            "https://byjus.com/theoretical-yield-formula","Theoretical Yield Formula",
            "https://byjus.com/titration-formula","Titration Formula",

            "https://byjus.com/combined-gas-law-formula","Combined Gas Law Formula",
            "https://byjus.com/degree-of-unsaturation-formula","Degree of Unsaturation Formula",

            "https://byjus.com/electron-dot-formula","Electron Dot Formula",
            "https://byjus.com/henrys-law-formula","Henry&#8217;s Law Formula",

            "https://byjus.com/ionization-energy-formula","Ionization Energy Formula",
            "https://byjus.com/molality-formula","Molality Formula",

            "https://byjus.com/mole-fraction-formula","Mole Fraction Formula",
            "https://byjus.com/partial-pressure-formula","Partial Pressure Formula",

            "https://byjus.com/partition-coefficient-formula","Partition Coefficient Formula",
            "https://byjus.com/stp-formula","STP Formula"};

    public static void main(String[]args){
        for(int i = 0;i<chemURLs.length;i+=2){
            ConvertUrlstoText(chemURLs[i]);
        }
    }



    public static void ConvertUrlstoText(String w){

        try {
            String webPage = w+"/";
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String temp = "";

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                temp = processHTMLLine(charArray.toString());
                sb.append(charArray, 0, numCharsRead);
                /*
                temp = processHTMLLine(charArray.toString());
                sb.append(temp, 0, numCharsRead);*/
            }
            System.out.println("*** BEGIN ***");
            System.out.println(filterHTMLtotext(sb.toString()));
            System.out.println("*** END ***");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String filterHTMLtotext(String res){
        Scanner scan = new Scanner(res);
        String ans = "";
        String temp = "";
        while (scan.hasNextLine()){
            temp = scan.nextLine();
            if(temp.contains("Practise This Question"))
                break;
            if(temp.length()!=0){
                if(temp.contains("<p>")||temp.contains("<br >")){
                    ans+=processHTMLLine(temp)+"\n";
                }
            }
        }
        return ans;
    }

    public static String processHTMLLine(String s){

        if(s.contains("<p>")){
            if(s.indexOf('<')==0)
            return s.substring(s.indexOf('>')+1,s.lastIndexOf('<'));
        }
        else if(s.contains("<br >"))
        {
            return s.substring(0, s.indexOf('<'));
        }
        return "";
    }
}



