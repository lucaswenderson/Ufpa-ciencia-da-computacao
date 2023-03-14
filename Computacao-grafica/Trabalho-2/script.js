HEIGHT = window.innerHeight;
WIDTH = window.innerWidth;

let mousePos= {x:0, y:0};

var Colors = {
  verdeMarinho: 0x38bbb7,
  azul:0x045c7c,
  azulEscuro:0x0d324e,
  cinza:0xaaaaaa,
  preto:0x000000,
  branco:0xffffff,
  vinho:0x8B0000,
  prata:0x7A8386,
  roxo: 0x800080,
  verde: 0x008000,  
};

const Pi = Math.PI,    
      scene = new THREE.Scene(),
      camera = new THREE.PerspectiveCamera(60, WIDTH/HEIGHT, 1, 10000),
      renderer = new THREE.WebGLRenderer({alpha: true, antialias: true}),
      container = document.getElementById('space'),  
      ambientLight = new THREE.AmbientLight(Colors.branco, .8),
      hemisphereLight = new THREE.HemisphereLight(Colors.branco,Colors.branco, 1),
      shadowLight = new THREE.DirectionalLight(Colors.branco, .9);

const models = { //all the variables behind the objects in the scene
    planet : {
        radius: 660,
        height: 700,
        rSeg:50,
        hSeg:20,
        ampR: 20,
        speedC: .015,
        color: Colors.verde,
        rotation: .005,
        x: 0,
        y: -620,
        z: -50,
        step: .000005, 
    },
    asteroid : {
      size: 5+Math.random()*50,
      spreadX: 200,
      maxWidth: WIDTH,
      maxHeight: 300,
      maxDepth: 400
    },
    space : {
        groupNumber : 22,
        membersNumber : 20,
        depth: 450,
        step: .0015,
    },
    bomb : {
        innerR : 8,
        outerR : 16,
        rSegments : 8,
        tSegments : 20,
        number: 25,
        step: .003,
    },
    spaceship : {
        y : 100,
        z : 110,
        minX: -350,
        maxX: 350,
        minY: 70,
        maxY: 450,
    },
};

const settings={
  camera: {
    x:0,
    y:350,
    z:600,
    xRot: -Pi/32,
  },
};

params = {
  jsize:1,
  speed:1,
  tsize:1,
};

let space,
    trash, 
    trashHolder, 
    spaceship, 
    jellyDisplacementX = 0, 
    jellyDisplacementY = 0, 
    crashSpeedX = 0, 
    crashSpeedY = 0,
    crash;

function initScene() { 
  scene.fog = new THREE.Fog(Colors.azulEscuro, -200,950);
  
  camera.position.set(settings.camera.x,settings.camera.y,settings.camera.z);
  camera.rotation.x=settings.camera.xRot;
  
  renderer.setSize(WIDTH, HEIGHT);  
	renderer.shadowMap.enabled = true;
  
	container.appendChild(renderer.domElement);
	window.addEventListener('resize', handleWindowResize, false);
}
function handleWindowResize() {
	HEIGHT = window.innerHeight;
	WIDTH = window.innerWidth;
	renderer.setSize(WIDTH, HEIGHT);
	camera.aspect = WIDTH / HEIGHT;
	camera.updateProjectionMatrix();
}
function normalize(v,vmin,vmax,tmin, tmax){
	const nv = Math.max(Math.min(v,vmax), vmin);
	const dv = vmax-vmin;
	const pc = (nv-vmin)/dv;
	const dt = tmax-tmin;
	const tv = tmin + (pc*dt);
	return tv;
}
function handleMouseMove(event) {
    const tx = -1 + (event.clientX / WIDTH)*2;
    const ty = 1 - (event.clientY / HEIGHT)*2;
    mousePos = {x:tx, y:ty};
}
function initLights() {
    scene.add(hemisphereLight);
    scene.add(shadowLight);
    scene.add(ambientLight);
}


Planet = function(rad,h,rS,hS,a,sC,color,xP,yP,zP){
    const geometry = new THREE.CylinderGeometry(rad,rad,h,rS,hS);
    geometry.applyMatrix(new THREE.Matrix4().makeRotationX(-Pi/2));
    geometry.mergeVertices();
    const length = geometry.vertices.length;
  
    this.bumps = [];
    for(let i=0; i<length; i++){
        const v = geometry.vertices[i];
        this.bumps.push({x : v.x,
                         y : v.y,
                         z : v.z, 
                         ang: Math.random()*Pi*2,
                         amp: Math.random()*a,
                         speed: sC + Math.random()*sC});
    }

    const material = new THREE.MeshPhongMaterial({ 
        color: color,
        transparent:true,
        opacity:.99,
        flatShading:true,
    });

    this.mesh = new THREE.Mesh(geometry, material);
    this.mesh.receiveShadow = true;
    this.mesh.position.set(xP,yP,zP);
}
Planet.prototype.moveBumps = function (step){
	const verts = this.mesh.geometry.vertices;
	const length = verts.length;
	
	for (let i=0; i<length; i++){
		const v = verts[i];
		const vprops = this.bumps[i];
		v.x = vprops.x + Math.cos(vprops.ang)*vprops.amp;
		v.y = vprops.y + Math.sin(vprops.ang)*vprops.amp;
        vprops.ang += vprops.speed;
	}

	this.mesh.geometry.verticesNeedUpdate=true;
	planet.mesh.rotation.z += step;
}
const planet = new Planet(models.planet.radius, models.planet.height,
                          models.planet.rSeg, models.planet.hSeg,
                          models.planet.ampR, models.planet.speedC, models.planet.color,
                          models.planet.x, models.planet.y, models.planet.z);
scene.add(planet.mesh);


Space = function(gN,mN,d){
    this.mesh = new THREE.Object3D();
    this.objects = [];
    const step = Pi*2 / gN;
    let angle, object, type, offset, depth;
    for(let j = 0; j < gN; j++){
        angle = step*j;
        offset = (Pi/16)*(Math.random()*0.4 + 0.8); 

        for(let i = 0; i  < mN; i++){
            
            type = Math.floor(Math.random()*30); 
            if(type<8) {
                offset = (Pi/4)*(Math.random()*0.4 - 0.8);
                object = new Asteroid(models.asteroid.size,models.asteroid.spreadX,models.asteroid.maxWidth,models.asteroid.maxHeight,models.asteroid.maxDepth);
                object.mesh.rotation.z= Math.random()*Pi*2;
            }
            else {
                offset = (Pi/8)*(Math.random()*0.4 - 0.8);
                object = new Asteroid(models.asteroid.size/2,models.asteroid.spreadX,models.asteroid.maxWidth,models.asteroid.maxHeight,models.asteroid.maxDepth);
                object.mesh.rotation.z = angle + offset;
            }
            object.mesh.position.z = 0-Math.random()*d;
            this.objects.push(object);
            depth = models.planet.height + Math.random()*d*1.5;
            object.mesh.position.y = Math.sin(angle + offset)*(depth);
            object.mesh.position.x = Math.cos(angle + offset)*(depth);
            object.angle = angle + offset;
            this.mesh.add(object.mesh);
        }      
    }
}
function createSpace(y){
      space = new Space(models.space.groupNumber, models.space.membersNumber,models.space.depth);
      space.mesh.position.y = models.planet.y;
      scene.add(space.mesh);
}

const Asteroid = function(size,spreadX,maxWidth,maxHeight,maxDepth){
    this.mesh = new THREE.Object3D();
    this.mesh.name = "Asteroid";

    geomAst = new THREE.DodecahedronGeometry(size, 1);
    geomAst.vertices.forEach(function(v){
      v.x += (0-Math.random()*(size/4));
      v.y += (0-Math.random()*(size/4));
      v.z += (0-Math.random()*(size/4));
    })
    var color = '#111111';
    color = new THREE.Color("rgb(255,"+ Math.floor(95 + Math.random()*100) +","+ 
                            (Math.floor(Math.random()*20))+")"),
    texture = new THREE.MeshStandardMaterial({color:color,flatShading: THREE.FlatShading,roughness: 0.8,metalness: 1});

    Aster = new THREE.Mesh(geomAst, texture);
    Aster.castShadow = true;
    Aster.receiveShadow = true;
    Aster.scale.set(1+Math.random()*0.4,1+Math.random()*0.8,1+Math.random()*0.4);
    var x = spreadX/2-Math.random()*spreadX;
    var centeredness = 1-(Math.abs(x)/(maxWidth/2));
    var y = (maxHeight/2-Math.random()*maxHeight)*centeredness
    var z = (maxDepth/2-Math.random()*maxDepth)*centeredness
    Aster.position.set(x,y,z)
    Aster.r = {};
    Aster.r.x = Math.random() * 0.005;
    Aster.r.y = Math.random() * 0.005;
    Aster.r.z = Math.random() * 0.005;
    this.mesh.add(Aster);  
}
const Bomb = function(iR,oR,rS,tS,sc){
    this.mesh = new THREE.Object3D();
    const materialBody = new THREE.MeshPhongMaterial({color: Colors.vinho, flatShading:true,});
    const geometryBody = new THREE.TorusGeometry( oR, iR, rS, tS );

    const body = new THREE.Mesh(geometryBody,materialBody);
  
    const materialPattern= new THREE.MeshPhongMaterial({color: Colors.branco, flatShading:true,})
    const geometryPattern = new THREE.TorusGeometry( oR*1.01, iR, rS, tS, Pi/16);
    geometryPattern.openEnded=true;

    for(let i=0; i<16; i++){
        const pattern = new THREE.Mesh(geometryPattern, materialPattern);
        pattern.rotation.z+=i*Pi/8;
        this.mesh.add(pattern);
    }
    this.displX = 0;
    this.displY = 0;
    this.mesh.castShadow = true;
    this.mesh.receiveShadow = true;
    this.mesh.scale.set(sc,sc,sc);
    this.mesh.add(body);
}

BombHolder = function(){
    this.mesh = new THREE.Object3D();
    this.elements = [];
}
BombHolder.prototype.spawnBomb = function(d,z,n){
    for(let i=0; i<n; i++){
        const trash = new Bomb(models.bomb.innerR,models.bomb.outerR,models.bomb.rSegments,models.bomb.tSegments,1);
        trash.angle =2*Pi*i/n -  Math.random()*.3;
        trash.angleCopy = trash.angle;
        trash.distance = d + 50 + Math.random()*50;
        trash.offset = Math.random()*350;
        trash.mesh.rotation.y = Math.random()*Pi;
        trash.mesh.rotation.z = Math.random()*Pi;
        trash.mesh.position.z= z;
        trash.mesh.position.y =trash.offset - models.planet.height + Math.sin(trash.angle)*trash.distance;
        trash.mesh.position.x = Math.cos(trash.angle)*trash.distance;
        this.mesh.add(trash.mesh);  
        this.elements.push(trash);
    }
}
BombHolder.prototype.rotateBomb = function(step,scale){
    for(let i = 0; i <this.elements.length; i++){
        const singleBomb = this.elements[i];
        singleBomb.angle+=step;
        singleBomb.mesh.position.y= singleBomb.offset - models.planet.height*.95 + Math.sin(singleBomb.angle)*(singleBomb.distance) ;
        singleBomb.mesh.position.x = Math.cos(singleBomb.angle)*(singleBomb.distance);
        singleBomb.mesh.scale.set(scale,scale,scale);
        const diffPos = spaceship.mesh.position.clone().sub(singleBomb.mesh.position.clone());
        const d = diffPos.length();
        if(d<2*models.bomb.outerR*params.tsize + params.jsize*4){
            crashSpeedX = 120*diffPos.x / (d);
            crashSpeedY = 120*diffPos.y / (d);
            crash = i;
        }
        if(crash == i && crashSpeedX!=0){
            singleBomb.mesh.position.y= singleBomb.offset - models.planet.height*.95 + Math.sin(singleBomb.angle )*(singleBomb.distance) - jellyDisplacementY/10;
            singleBomb.mesh.position.x= Math.cos(singleBomb.angle)*(singleBomb.distance) - jellyDisplacementX/5 ;
        }
    }
}
BombHolder.prototype.hide = function(){
    for(let i = 0; i <this.elements.length; i++){
        if(this.elements[i].mesh.position.y<-50) this.elements[i].mesh.visible = false;
        else  this.elements[i].mesh.visible = true;
    }
}
function createBomb(){
    trashHolder = new BombHolder();
    trashHolder.spawnBomb(models.planet.height,110,models.bomb.number);
    scene.add(trashHolder.mesh)
}


const SpaceShip = function() {    
	this.mesh = new THREE.Object3D();	
  const points = [];
  points.push(new THREE.Vector2(0, -12));
  points.push(new THREE.Vector2(0.5, -12));
  points.push(new THREE.Vector2(1.5, -11.75));
  points.push(new THREE.Vector2(2.5, -11.5));    
  points.push(new THREE.Vector2(3.5, -11));
  for ( let i = 0; i < 12; i+=2.25 ) points.push( new THREE.Vector2( Math.sin( i * 0.2 ) * 10 + 5, ( i - 5 ) * 2 ) );
	points.push(new THREE.Vector2(0, 11));
    

	const geomBody = new THREE.LatheBufferGeometry( points , 8 ,0, Pi*2);
  const material = new THREE.MeshPhongMaterial({color:0x99FF00, flatShading:true, transparent:true, opacity:.8});
  const body = new THREE.Mesh(geomBody, material);
  const geomInside = new THREE.SphereGeometry(10,8,8);
  const materialIn = new THREE.MeshPhongMaterial({color:0xFFFF00, flatShading:true, side:THREE.DoubleSide});
  const inside = new THREE.Mesh(geomInside, materialIn);
  inside.scale.set(1,1.5,1);
  inside.position.x-= 5.25;
  this.mesh.add(inside);
	body.rotation.z = Pi/2;
	body.castShadow = true;
	body.receiveShadow = true;
	this.mesh.add(body);
};
function updateSpaceShip(scale,minX,maxX,minY,maxY){
  spaceship.mesh.rotation.x+=.005;
	let targetX = normalize(mousePos.x, -1, 1, minX, maxX);
  let targetY = normalize(mousePos.y, -1, 1, minY, maxY);
   
  jellyDisplacementX+= crashSpeedX;
  targetX+=jellyDisplacementX;

  jellyDisplacementY+= crashSpeedY;
  targetY+=jellyDisplacementY;

  spaceship.mesh.position.y+= (targetY - spaceship.mesh.position.y)*0.02;
  spaceship.mesh.position.x+= (targetX - spaceship.mesh.position.x)*0.03;
  spaceship.mesh.rotation.z = (targetY-spaceship.mesh.position.y)*0.0050;
	spaceship.mesh.rotation.x = (spaceship.mesh.position.x-targetX)*0.0025;

  crashSpeedX+= -1*crashSpeedX*0.07;
  crashSpeedY+= -1*crashSpeedY*0.07;
  jellyDisplacementX+= -1*jellyDisplacementX*0.3;
  jellyDisplacementY+= -1*jellyDisplacementY*0.3;
  
  spaceship.mesh.scale.set(scale,scale,scale);
    
}
function createSpaceShip(){
	spaceship = new SpaceShip();
	spaceship.mesh.scale.set(1.35,1.5,1.5);
	spaceship.mesh.position.y = models.spaceship.y;
	spaceship.mesh.position.z = models.spaceship.z;
  scene.add(spaceship.mesh);
}

function loop(){    
    if(!settings.oNpause){
        planet.mesh.rotation.z += models.planet.step*params.speed;
        space.mesh.rotation.z+= models.space.step*params.speed;
        planet.moveBumps(models.planet.rotation);
        trashHolder.rotateBomb(models.bomb.step*params.speed,params.tsize);
        trashHolder.hide();
        updateSpaceShip(params.jsize,
                        models.spaceship.minX,
                        models.spaceship.maxX,
                        models.spaceship.minY,
                        models.spaceship.maxY);
    }
    else{
        changeSliders();
    }
    
    renderer.render(scene, camera);
    requestAnimationFrame(loop);
}

function init(event){
    document.addEventListener('mousemove', handleMouseMove, false);
    initScene();
    initLights();
    createSpaceShip();
    createBomb();
    createSpace();
    
    loop();
}

window.addEventListener('load', init, false);





