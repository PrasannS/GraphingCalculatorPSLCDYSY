package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;

public class FormulaListGenerator {

    public static String[] mathFormulae = {"Algebra Formulas","Associative Property",
            "Absolute Value Formula","Addition Formula",
            "Anova Formula","Average Rate of Change Formula",
            "Asymptote Formula","Axis of Symmetry Formula",
            "Angle Formula","Area of a Triangle Formula",
            "Arithmetic Sequence Formula","Average Deviation Formula",
            "Area Formulas","Area of a Pentagon Formula",
            "Area of a Trapezoid Formula","Area of a Rectangle Formula",
            "Antiderivative Formula","Area of a Sector of a Circle Formula",
            "Arc Length Formula","Area Formula for Quadrilaterals",
            "Area of a Sector Formula","Area of a Rhombus Formula",
            "Area of a Parallelogram Formula","Area of a Square Formula",
            "Area of a Circle Formula","Area of Regular Polygon Formula",
            "Arithmetic Sequence Explicit Formula","Area of a Hexagon Formula",
            "Area of an Octagon Formula","Area under the Curve Formula",
            "Area of Isosceles Triangle Formula","Binary Formula",
            "Area of a Segment of a Circle Formula","Arithmetic Sequence Recursive Formula",
            "Annulus Formula","Arithmetic Mean Formula",
            "Binomial Distribution Formula","Arctan Formula",
            "Binomial Probability Formula","Binary to Decimal Formula",
            "Basic Math Formulas","Cofunction Formulas",
            "Area of Equilateral Triangle Formula","Calculus Formulas",
            "Commutative Property","Binomial Expansion Formula",
            "Chi Square Formula","Bayes Theorem Formula",
            "Conditional Probability Formula","Correlation Coefficient Formula",
            "Complex Number Formula","Covariance Formula",
            "Combination Formula","Confidence Interval Formula",
            "Cpk Formula","Coefficient of Determination Formula",
            "Chain Rule Formula","Coin Toss Probability Formula",
            "Celsius Formula","Compound Interest Formula",
            "Cp Formula","Cube Root Formula",
            "Central Limit Theorem Formula","Completing the Square Formula",
            "Covariance Matrix Formula","Change of Base Formula",
            "Complex Number Division Formula","Cross Product Formula",
            "Cubic Equation Formula","Coefficient of Variation Formula",
            "Cube Formula","Central Angle of a Circle Formula",
            "Cofactor Formula","Complex Number Power Formula",
            "Circumference Formula","Cosine Formula",
            "Difference Quotient Formula","Consecutive Integers Formula",
            "Circle Graph Formula","Centroid of a Trapezoid Formula",
            "Chord Length Formula","Direct Variation Formula",
            "Circumcenter Formula","Division Formula",
            "Derivative Formula","Decay Formula",
            "Dot Product Formula","Vector Formulas",
            "De Moivre Formula","Volume of a Sphere Formula",
            "Decimal to Fraction Formula","Daily Compound Interest Formula",
            "Diagonal Formula","Determinant Formula",
            "Direction of a Vector Formula","Diagonal of a Cube Formula",
            "Definite Integral Formula","Difference of Cubes Formula",
            "Ellipse Formula","Equation Formula",
            "Discriminant Formula","Exponential Function Formula",
            "Empirical Probability Formula","Double Angle Formulas",
            "Effect Size Formula","Equation of a Circle Formula",
            "Exponents Formula","Gross Profit Formula",
            "Exponential Distribution Formula","Foil Formula",
            "Fibonacci Formula","Equilateral Triangle Formula",
            "F Test Formula","Geometry Formulas",
            "Frustum of a Right Circular Cone Formula","Function Notation Formula",
            "Geometric Sequence Formula","Geometric Distribution Formula",
            "Fourier Series Formula","Hyperbolic Function Formula",
            "Factorial Formula","Hyperbola Formula",
            "Half Angle formula","Graphs of Trigonometric Functions Formula",
            "Half Life Formula","Inverse Variation Formula",
            "Geometric Series Formula","Interest Formula",
            "Infinite Series Formula","Hypothesis Testing Formula",
            "Implicit Differentiation Formula","Height of a Parallelogram Formula",
            "Inverse Function Formula","Integration by Substitution Formula",
            "Inverse Hyperbolic Functions Formula","Isosceles Trapezoid Formula",
            "Law of Sines Formula","Isosceles Triangle Perimeter Formula",
            "Linear Regression Formula","Loan Balance Formula",
            "Lagrange Interpolation Formula","Interquartile Range Formula",
            "Linear Approximation Formula","X and Y Intercept Formula",
            "Limit Formula","Mean Deviation Formula",
            "Maclaurin Series Formula","Magnitude of a Vector Formula",
            "Monthly Compound Interest Formula","Normal Distribution Formula",
            "Orthocenter Formula","Newton’s Method Formula",
            "Percentage Formula","Pyramid Formula",
            "Profit Margin Formula","Parallelogram Formula",
            "Product to Sum Formula","Population Mean Formula",
            "Partial Differential Equations","Product Rule Formula",
            "Profit Formula","Perfect Square Trinomial Formula",
            "Percentage Increase Formula","Perimeter of a Square Formula",
            "T-Distribution Formula","Point of Intersection Formula",
            "Perimeter of a Triangle Formula","Perpendicular Formula",
            "Pythagorean Triples Formula","Percentage Yield Formula",
            "Volume of a Rectangular Prism Formula","Revenue Formula",
            "Rectangle Formula","Relative Frequency Formula",
            "Pearson Correlation Formula","Ratio Analysis Formula",
            "Platonic Solids Formula","Regression Sum of Squares Formula",
            "Quotient Rule Formula","Statistics Formulas",
            "Resultant Vector Formula","Standard Form Formula",
            "Right Triangle Formula","Slope Formula",
            "Root Mean Square Formula","Summation Formula",
            "Radius Formula","Resistors in Parallel Formula",
            "Subtraction Formulas","Series Formula",
            "Signal to Noise Ratio Formula","Sphere Formula",
            "Surface Area of a Cone Formula","Sample Size Formula",
            "Square Root Property Formula","Surface Area of a Prism Formula",
            "Sample Mean Formula","Simple Interest Formula",
            "Sum of Squares Formula","Spherical Cap Volume Formulas",
            "Spherical Sector Formula","Slant Asymptote Formula",
            "Tangent Formula","Surface Area of a Rectangular Prism Formula",
            "Taylor Series Formula","Triangular Pyramid Formula",
            "Trigonometry Formulas","The Distance Formula",
            "Unit Circle Formula","T Test Formula",
            "Vector Projection Formula","Unit Rate Formula",
            "Volume of a Cone Formula","Volume Formulas",
            "Volume of a Triangular Prism Formula","Volume of a Cube Formula",
            "X Intercept Formula","Volume of an Ellipsoid Formula",
            "Vieta’s Formula","Y Intercept Formula",
            "Distributive Property","Difference of Squares Formula",
            "Linear Interpolation Formula","Discount Formula",
            "Linear Correlation Coefficient Formula","Mean Median Mode Formula",
            "Diameter Formula","Multiple Angle Formulas",
            "Natural Log Formula","Probability Formulas",
            "Parabola Formula","Pythagorean Theorem Formula",
            "Probability Distribution Formula","Trapezoidal Rule Formula",
            "Euler Maclaurin Formula","Exponential Equation Formula",
            "Factoring Formulas","Function Formulas",
            "Percentage Change Formula","Percent Error Formula",
            "Perimeter of a Parallelogram Formula","Perimeter of a Kite Formula",
            "Volume Charge Density Formula","Triangle Formula",
            "Perimeter of Hexagon Formula","Quadratic Function Formula",
            "Degree and Radian Measure Formula","Degrees of Freedom Formula",
            "Decimal to Binary Formula","Double Time Formula",
            "Rate of Change Formula","Reduction Formula",
            "Radians to Degrees Formula","Diagonal of Parallelogram Formula",
            "Equation of a Formula","Exponential Growth Formula",
            "Euler’s Formula","Factoring Trinomials Formula",
            "Fahrenheit to Celsius Formula","Great Circle Formula",
            "Geometric Mean Formula","Hypergeometric Distribution Formula",
            "Rectangular Parallelepiped Formula","Radius of Curvature Formula",
            "Surface Area Formulas","Square Root Formula",
            "Skewness Formula","Sampling Error Formula",
            "Surface Area of a Pyramid Formula","Simpson’s Rule Formula",
            "Surface Area of a Cylinder Formula","Harmonic Mean Formula",
            "Integral Formula","Isosceles Triangle Formula",
            "Infinite Geometric Series Formula","Stirling Formula",
            "Spherical Segment Formula","Temperature Conversion Formula",
            "Tangent Addition Formula","Tangent Circle Formula",
            "U Substitution Formula","Variance Formula",
            "Volume of a Cylinder Formula","Volume of a Square Pyramid Formula",
            "Weighted Average Formula","Z Score Formula",
            "Frequency Distribution Formula","Frustum of a Regular Pyramid Formula",
            "Gaussian Distribution Formula","Hexagon Formula",
            "Hexagonal Pyramid Formula","Interpolation Formula",
            "Inverse Matrix Formula","Integration by Parts Formula",
            "Law of Cosines Formula","LCM Formula",
            "Linear Function Formula","Midpoint Formula",
            "Mean Value Theorem Formula","N Choose K Formula",
            "Permutations and Combinations Formulas","Perimeter Formulas",
            "Polygon Formula","Logarithm Formula",
            "Line of Best Fit Formula","Linear Equations Formula",
            "Matrix Formula","Margin of Error Formula",
            "Mean Absolute Deviation Formula","Octagon Formula",
            "Polynomial Formula","Prism Formula",
            "Periodic Formulas","Permutation Formula",
            "Perfect Square Formula","Percentile Formula",
            "Point Slope Form Formula","Perimeter of a Trapezoid Formula",
            "Point Gradient Formula","Spherical Wedge and Spherical Lune Formula",
            "Perimeter of Rhombus Formula","Quadratic Interpolation Formula",
            "Ratio Formula","Riemann Sum Formula",
            "Relative Standard Deviation Formula","Regular Square Pyramid Formula",
            "Regular Tetrahedron Formula","Prime Number Formula",
            "Percentage Decrease Formula","Proportion Formula",
            "Perimeter of a Rectangle Formula","Parallel Formula",
            "Poisson Distribution Formula","Percent Composition Formula",
            "Quartile Formula","Square Footage Formula",
            "Sum of Cubes Formula","Sine Cosine Tangent Formula",
            "Standard Error Formula","Surface Area of a Cube Formula",
            "Radical Formula","Slope Intercept Form Formula",
            "Rotation Formula","Surface Area of Circle Formula",
            "R Squared Formula","Sine Formula",
            "Right Angle Formula","Sum of Arithmetic Sequence Formula",
            "Retention Factor Formula","Trajectory Formula",
            "Scientific Notation Formula","Tangential Quadrilateral Formula",
            "Sequence Formula","Tangent Formula",
            "Square Formula","Unit Vector Formula",
            "Standard Deviation Formula","Vertex Formula",
            "Statistical Significance Formula","Volume of Parallelepiped Formula",
            "Surface Area of a Triangular Prism Formula","Volume of a Pyramid Formula",
            "Side Angle Side Formula","Weighted Mean Formula",
            "Surface Area of a Sphere Formula","Surface Area of a Square Pyramid Formula"};


    public static String [] physicsFormulae = {"Absolute Pressure Formula","Acceleration Formula",
            "Angle between Two Vectors Formula","Average Force Formula",
            "Amplitude Formula","Acceleration due to Gravity Formula",
            "Archimedes Principle Formula","Ampere’s Law Formula",
            "Angular Acceleration Formula","Air Resistance Formula",
            "Angular Momentum Formula","Angular Displacement Formula",
            "Average Speed Formula","Angular Speed Formula",
            "Average Acceleration Formula","Angular Velocity Formula",
            "Buoyancy Formula","Beat Frequency Formula",
            "Brewster’s Law","Average Velocity Formula",
            "Bulk Modulus Formula","Beam Deflection Formula",
            "Capacitance Formula","Buffer Solution Formula",
            "Bernoulli’s Equation Formula","Center of Mass Formula",
            "Current Density Formula","Cone Formula",
            "Cylinder Formula","Circle Formula",
            "Cyclic Quadrilateral Formula","Capacitive Reactance Formula",
            "Centripetal Force Formula","Centroid Formula",
            "Circular Velocity Formula","Coulomb’s Law Formula",
            "Critical Angle Formula","Cylindrical Capacitor Formula",
            "Charge Density Formula","Combustion Formula",
            "Centripetal Acceleration Formula","Cell Potential Formula",
            "Calorimetry Formula","Conservation of Energy Formula",
            "Coefficient of Static Friction Formula","Distance Speed Time Formula",
            "Doppler Shift Formula","Deceleration Formula",
            "Electric Field Formula","DC Voltage Drop Formula",
            "Efficiency Formula","Elastic Potential Energy Formula",
            "Electric Power Formula","Energy Density Formula",
            "Friction Formula","Escape Speed Formula",
            "Friction Force Formula","Formula Dynamics",
            "Gravity Formula","Flow Rate Formula",
            "Gauss Law Formula","Gregory Newton Formula",
            "Heat Formula","Gram Formula Mass",
            "Heat of Vaporization Formula","Heat Transfer Formula",
            "Heat Gain Formula","Heat Load Formula",
            "Continuous Compound Interest Formula","Density Formula",
            "De Broglie Wavelength Formula","Doppler Effect Formula",
            "Displacement Formula","Diffraction Grating Formula",
            "Dynamic Viscosity Formula","Elastic Collision Formula",
            "Electric Current Formula","Force Formula",
            "Friction Loss Formula","Froude Number Formula",
            "Gravitational Field Formula","Hooke’s Law Formula",
            "Heat of Fusion Formula","Heat of Solution Formula",
            "Heat Conduction Formula","Heat of Hydration Formula",
            "Electric Potential Formula","Drag Force Formula",
            "Heat Input Formula","Heat Rate Formula",
            "Inductance Formula","Electrical Formulas",
            "Distance Traveled Formula","Inelastic Collision Formula",
            "Equivalent Resistance Formula","Instantaneous Rate of Change Formula",
            "Electricity Formulas","Inductive Reactance Formula",
            "Intensity Formula","Force of Attraction Formula",
            "Fluid Mechanics Formula","Escape Velocity Formula",
            "Kinematic Viscosity Formula","Gravitational Potential Energy Formula",
            "Energy Consumption Formula","Kinetic Energy Formula",
            "Gravitational Acceleration Formula","Linear Speed Formula",
            "Latent Heat formula","Frequency Formula",
            "Heat Capacity Formula","Lens Maker’s Formula",
            "Magnetism Formula","Free Fall Formula",
            "Heat Loss Formula","Momentum Formula",
            "Mechanical Energy Formula","Gravitational Force Formula",
            "Magnetic Force Formula","Momentum and Its Conservation",
            "Heisenberg Uncertainty Principle Formula","Mass Flow Rate Formula",
            "Newton’s Second Law Formula","Induced Voltage Formula",
            "Normal Force Formula","Orbital Velocity Formula",
            "Initial Velocity Formula","Optics Formula",
            "Power Formula","Kelvin to Celsius Formula",
            "Pressure Formula","Pressure Drop Formula",
            "Pendulum","Linear Momentum Formula",
            "Poiseuille’s Law formula","Reynolds Number Formula",
            "Measurement Formulas","Resultant Force Formula",
            "Relative Motion Formula","Heat Engine Efficiency Formula",
            "Gay Lussac Law Formula","Mechanical Advantage Formula",
            "Resistivity Formula","Relativistic Doppler Effect Formula",
            "Heat Release Rate Formula","Horsepower Formula",
            "Refractive Index Formula","Reflection and Ray Model of Light",
            "Magnetic Field Formula","Inverse Square Law Formula",
            "Heat Index Formula","Snell’s Law Formula",
            "Spring Force Formula","Mach Number Formula",
            "Instantaneous Velocity Formula","Heat Flux Formula",
            "Speed of Sound Formula","Strain Energy Formula",
            "Kinetic Friction Formula","Heat of Reaction formula",
            "Ohms Law Formula","Sound Intensity Formula",
            "Sound Pressure Level Formula","Lattice Energy Formula",
            "Impulse Formula","Torque Formula",
            "Potential Energy Formula","Time Constant Formula",
            "Light Waves and Color","Instantaneous Speed Formula",
            "Thermal Conductivity Formula","Transformer Formula",
            "Moment Formula","Parallel Plate Capacitor Formula",
            "Kinematics Formulas","Tangential Acceleration Formula",
            "Universal Gravitation Formula","Polarization Formula",
            "Length Contraction Formula","Velocity Formula",
            "Work Formula","Rotational Inertia Formula",
            "Work Done by Gravity Formula","Wavelength to Frequency Formula",
            "Wave Speed Formula","Wave Energy Formula",
            "Resistance Formula","Relative Velocity Formula",
            "Strain Formula","Specific Gravity Formula",
            "Specific Heat Formula","Stopping Distance Formula",
            "Thermal Expansion Formula","Tangential Velocity Formula",
            "Voltage Divider Formula","Weight Formula",
            "Water Pressure Formula","Magnetic Flux Formula",
            "Magnetic Field in a Solenoid Formula","Newton’s Law of Cooling Formula",
            "Physics Formulas","Potential Energy of a Spring Formula",
            "Latent Heat of Fusion Formula","Molar Concentration Formula",
            "Mass Formula","Moment of Inertia Formula",
            "Net Force Formula","Orbital Speed Formula",
            "Projectile Motion Formula","Position Formula",
            "Pascal’s Principle Formula","Photon Energy Formula",
            "Relativity Formula","Relativistic Mass Formula",
            "Refraction Formula","Stress Formula",
            "Static Friction Formula","Spring Constant Formula",
            "Surface Charge Density Formula","Temperature Formula",
            "Rotational Kinetic Energy Formula","Resonant Frequency Formula",
            "Radiant Energy Formula","Speed Distance Time Formula",
            "Surface Tension Formula","Time Dilation Formula",
            "Uniform Circular Motion","Wavelength Formula",
            "Wave Formula","Shear Modulus Formula",
            "Tension Formula","Thermal Energy Formula",
            "Terminal Velocity Formula","Young’s Modulus Formula",
            "Voltage Drop Formula","Wind Energy Formula",
            "Wave Power Formula"};

    public static String [] chemFormulae = {"Boyle’s Law Formula","Binding Energy Formula",
            "Atomic Mass Formula","Activation Energy Formula",
            "Chemical Formula","Bond Order Formula",
            "Dalton’s Law Formula","Chemical Reaction Formula",
            "Thermodynamics Formulas","Chemical Compound Formulas",
            "Enthalpy Formula","Dilution Formula",
            "Condensed Structural Formula","Gay Lussac’s Law Formula",
            "Entropy Formula","Density of Gas Formula",
            "Equilibrium Constant Formula","Gibbs Free Energy Formula",
            "Avogadro’s Law Formula","Boiling Point Formula",
            "Charles Law Formula","Diffusion Formula",
            "Empirical Formula","Grams to Moles Formula",
            "Ionic Compound Formula","Molar Mass Formula",
            "Mass Percent Formula","Net Ionic Formula",
            "Percent by Weight Formula","Rate of Decay Formula",
            "Specific Heat Capacity Formula","Vapor Pressure Formula",
            "Ideal Gas Law Formula","Limiting Reactant Formula",
            "Moles to Grams Formula","Normality Formula",
            "Photosynthesis Formula","Rydberg Formula",
            "Stoichiometry Formula","Gas Pressure Formula",
            "Internal Energy Formula","Molarity Formula",
            "Molar Volume Formula","Osmotic Pressure Formula",
            "pH Formula","Structural Formula",
            "Sensible Heat Formula","Ionic Strength Formula",
            "Molecular Formula","Molecular Weight Formula",
            "Number of Moles Formula","Percent by Volume Formula",
            "Radioactive Decay Formula","Solubility Formula",
            "Theoretical Yield Formula","Titration Formula",
            "Combined Gas Law Formula","Degree of Unsaturation Formula",
            "Electron Dot Formula","Henry’s Law Formula",
            "Ionization Energy Formula","Molality Formula",
            "Mole Fraction Formula","Partial Pressure Formula",
            "Partition Coefficient Formula","STP Formula"};

    /*public static void main(String [] args){
        String formulatest  = "!@#$%\n" +
                "speed under constant acceleration\n" +
                "\n" +
                "\n" +
                "Equation\n" +
                "!@#$%\n" +
                "v = a t | \n" +
                "v | speed\n" +
                "t | time\n" +
                "a | acceleration\n" +
                "\n" +
                "\n" +
                "Input values\n" +
                "!@#$%\n" +
                "time | 1 second\n" +
                "acceleration | 1 m/s^2 (meter per second squared)\n" +
                "\n" +
                "\n" +
                "Result\n" +
                "!@#$%\n" +
                "speed | 100 cm/s (centimeters per second)\n" +
                "= 2.237 mph (miles per hour)\n" +
                "= 3.6 km/h (kilometers per hour)\n" +
                "\n" +
                "\n";

        Formula f = new Formula(formulatest,2,physicsFormulae[1]);
        System.out.println(f.toString());
    }*/

    public FormulaListGenerator(){

    }

    public ArrayList<Formula> loadformulae (String filepath, int type, Context context)throws IOException{
        ArrayList<Formula>info = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new InputStreamReader(context.getAssets().open(filepath)));
            String FormulaTemp = "";
            String temp;
            scanner.nextLine();
            scanner.nextLine();
            int a = 0;
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine();
                if (temp.equals("$%$%")) {
                    if (type == 0) {
                        Formula f  =new Formula(FormulaTemp, type, mathFormulae[a]);
                        info.add(new Formula(FormulaTemp, type, mathFormulae[a]));
                        System.out.println(Formula.types[type]);
                        System.out.println(mathFormulae[a]);
                    }
                    if (type == 1) {
                        info.add(new Formula(FormulaTemp, type, chemFormulae[a]));
                        System.out.println(Formula.types[type]);
                        System.out.println(chemFormulae[a]);
                    }
                    if (type == 2) {
                        info.add(new Formula(FormulaTemp, type, physicsFormulae[a]));
                        System.out.println(Formula.types[type]);
                        System.out.println(physicsFormulae[a]);
                    }
                    FormulaTemp = "";
                    a++;
                } else if (temp.length() != 0) {
                    FormulaTemp += temp+"\n";
                }
            }
            scanner.close();
        }
        catch (Exception e){
            Log.d("ScannerError","Scanner not working",e);
        }
        return info;
    }



}
